/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author elder
 */
    
public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
    
    private String mensagemMotorista;
    private String mensagemPassageiro;
    private int idmensagemM = 0;
    private int idmensagemP = 0;
    
    public CliImpl() throws RemoteException {

    }

    @Override
    //Atualiza a mensagem do passageiro com os dados do motorista
    public void notificarMotorista(String dadosPassageiro) throws RemoteException {
        
        System.out.println(dadosPassageiro);
        mensagemMotorista = dadosPassageiro;
        idmensagemM++;
    }    
    
    @Override
    //Atualiza a mensagem do motorista com os dados do passageiro
    public void notificarPassageiro(String dadosMotorista) throws RemoteException {    
        System.out.println(dadosMotorista);
        mensagemPassageiro = dadosMotorista;
        idmensagemP++;
    }

    public String getMensagemMotorista() throws RemoteException{
        
        return mensagemMotorista;
    }    
    
    public String getMensagemPassageiro() throws RemoteException{
        
        return mensagemPassageiro;
    }

    public int getIdmensagemM() {
        return idmensagemM;
    }

    public int getIdmensagemP() {
        return idmensagemP;
    } 
    
}