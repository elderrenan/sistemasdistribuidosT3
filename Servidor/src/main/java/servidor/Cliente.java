/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

/**
 *
 * @author elder
 * 
 */

public class Cliente {
    
    private final int id;
    private final String nome;
    private final String telefone;
    SseBroadcaster broadcaster;
    
    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }

    public void setBroadcaster(SseBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public SseBroadcaster getBroadcaster() {
        return broadcaster;
    }  
   
    public void registraBroadcaster(int id, SseBroadcaster broadcaster) {
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(broadcaster, OutboundEvent.class);
        adicionaBroadcast(id, jsonString);
                
    }    
    
    public int cadastraCliente(Cliente c) {

        int idR;
        Connection con = Conectar.getConnection();

        try{
            
            PreparedStatement pStm;             
            pStm = con.prepareStatement("insert into cliente (nome, telefone) values (?,?)", Statement.RETURN_GENERATED_KEYS);          
            pStm.setString(1, c.getNome());
            pStm.setString(2, c.getTelefone());
            pStm.executeUpdate();
            ResultSet rs;
            rs = pStm.getGeneratedKeys();
            idR = rs.getInt(1);
            rs.close();            
            pStm.close();
            con.close();
            
            return idR;
                                 
        }catch(SQLException e){
            e.printStackTrace();
        }
    
        return -1;
        
    }
    
    public ArrayList<Cliente> listaClientes() {
        
        Connection con = Conectar.getConnection();
    
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            PreparedStatement pStm;
            pStm = con.prepareStatement("Select * from cliente");
            ResultSet rs;
            rs = pStm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"));
                
                Gson gson = new Gson();
                c.setBroadcaster(gson.fromJson(rs.getString("broadcast"), SseBroadcaster.class));

                lista.add(c);
            }
            rs.close();
            pStm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }    
    
    public void deletarMaterial(int codigo){

        Connection con = Conectar.getConnection();
        
        try{
            
            PreparedStatement pStm;             
            pStm= con.prepareStatement("delete from cliente where codigo = ?");

            pStm.setInt(1, codigo);
            
            pStm.executeUpdate();    
            pStm.close();            
                                 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }    
    

    public void adicionaBroadcast(int id, String broadcast){

        Connection con = Conectar.getConnection();
        
        try{
            
            PreparedStatement pStm;             
            pStm= con.prepareStatement("UPDATE cliente SET broadcast = ? WHERE id = ?");                     
            pStm.setString(1, broadcast);
            pStm.setInt(2, id);
            pStm.executeUpdate();    
            pStm.close();            
            con.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}