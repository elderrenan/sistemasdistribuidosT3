/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemasdistribuidos;

import java.rmi.*;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author elder
 */
public interface InterfaceServ extends Remote {
    
    void cadastrarUsuario(InterfaceCli referenciaCliente, String nome, String telefone) throws RemoteException;
      
    ArrayList<String> listaCaronas(InterfaceCli referenciaCliente, String origem, String destino, String data) throws RemoteException;
    
    int interesseEmMotorista(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data, PublicKey pubKey, String mensagem, byte[] assinatura) throws RemoteException;
    
    void cancInteresseEmMotorista(int id) throws RemoteException;
    
    int interesseEmPassageiro(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data, int passageiros, PublicKey pubKey, String mensagem, byte[] assinatura) throws RemoteException;
    
    void cancInteresseEmPassageiro(int id) throws RemoteException; 
    
}
