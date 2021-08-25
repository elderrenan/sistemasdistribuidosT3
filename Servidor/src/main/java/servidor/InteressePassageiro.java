/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author elder
 */
public class InteressePassageiro {

    private final int id;
    private final String nome;
    private final String telefone;
    private final String origem;
    private final String destino;
    private final String data;
    private final int passageiros;
    
    public InteressePassageiro(int id, String nome, String telefone, String origem, String destino, String data, int passageiros) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.passageiros = passageiros;
    }
    
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getData() {
        return data;
    }

    public int getPassageiros() {
        return passageiros;
    }  

    public int getId() {
        return id;
    }
       
    public int cadastraMotorista(InteressePassageiro p){
        
        int id;
        Connection con = Conectar.getConnection();
        
        try{
            
            PreparedStatement pStm;
            pStm = con.prepareStatement("insert into interessepassageiro (nome, telefone, origem, destino, data, passageiros) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
            pStm.setString(1, p.getNome());
            pStm.setString(2, p.getTelefone());
            pStm.setString(3, p.getOrigem());
            pStm.setString(4, p.getDestino());
            pStm.setString(5, p.getData());
            pStm.setInt(6, p.getPassageiros());
            pStm.executeUpdate();     
            ResultSet rs;
            rs = pStm.getGeneratedKeys();
            id = rs.getInt(1);
            rs.close();            
            pStm.close();
            con.close();
            
            return id; 
                             
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return -1; 
    }
    
    public ArrayList<InteressePassageiro> listaMotoristas() {
        
        Connection con = Conectar.getConnection();
    
        ArrayList<InteressePassageiro> lista = new ArrayList<>();

        try {
            PreparedStatement pStm;
            pStm = con.prepareStatement("Select * from interessepassageiro");
            ResultSet rs;
            rs = pStm.executeQuery();
            while (rs.next()) {
                InteressePassageiro p = new InteressePassageiro(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getString("data"),
                    rs.getInt("passageiros"));
                lista.add(p);
            }
            rs.close();
            pStm.close();
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    } 
    
}