/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author elder
 */

public class Servidor {

   public static void main(String[] args)  {
   
        //System.setSecurityManager(new RMISecurityManager());
       
        try{
           
            Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);

            InterfaceServ referenciaServidor = new ServImpl();

            referenciaServicoNomes.rebind("Caronas", referenciaServidor);
        
        }catch(RemoteException e) {
            System.out.println("Servidor: " + e.getMessage());
        }
   }
}
