/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.rmi.*;

/**
 *
 * @author elder
 */
public interface InterfaceCli extends Remote {
    
    void notificarMotorista(String texto) throws RemoteException;
    
    void notificarPassageiro(String texto) throws RemoteException;

}
