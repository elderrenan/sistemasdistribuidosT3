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
 * 
 */

public class InteresseMotorista {
    
    private final int id;
    private final String nome;
    private final String telefone;
    private final String origem;
    private final String destino;
    private final String data;
   
    public InteresseMotorista(int id, String nome, String telefone, String origem, String destino, String data) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
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

    public int getId() {
        return id;
    }
      
    public int cadastraPassageiro(InteresseMotorista p){
        
        int idR;
        Connection con = Conectar.getConnection();
        
        try{
            
            PreparedStatement pStm;
            pStm = con.prepareStatement("insert into interessemotorista (nome, telefone, origem, destino, data) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
            pStm.setString(1, p.getNome());
            pStm.setString(2, p.getTelefone());
            pStm.setString(3, p.getOrigem());
            pStm.setString(4, p.getDestino());
            pStm.setString(5, p.getData());
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

    public ArrayList<InteresseMotorista> listaPassageiros() {
        
        Connection con = Conectar.getConnection();
    
        ArrayList<InteresseMotorista> lista = new ArrayList<>();

        try {
            PreparedStatement pStm;
            pStm = con.prepareStatement("Select * from interessemotorista");
            ResultSet rs;
            rs = pStm.executeQuery();
            while (rs.next()) {
                InteresseMotorista c = new InteresseMotorista(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getString("data"));
                lista.add(c);
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