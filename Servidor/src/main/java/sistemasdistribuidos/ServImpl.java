/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elder
 */

public class ServImpl extends UnicastRemoteObject implements InterfaceServ {
    
    ArrayList<Cliente> clientes = new ArrayList<>(); //Lista de clientes
    ArrayList<InteresseCarona> interesseMotorista = new ArrayList<>(); //Lista de interesse em motoristas
    ArrayList<InteressePassageiro> interessePassageiro = new ArrayList<>(); //Lista de interesse em passageiros
    
    public ServImpl() throws RemoteException{

    }    
    
    @Override
    public void cadastrarUsuario(InterfaceCli referenciaCliente, String nome, String telefone) throws RemoteException {

        //Adiciona cliente na lista de clientes
        clientes.add(new Cliente(nome, telefone));
        
        //Mostra todos os clientes cadastrados no console
        clientes.forEach(c -> {
            System.out.println("Nome: " + c.getNome());
        });

        System.out.println();
        
    }       
    
    @Override
    //Retorna a lista de corona cadastradas que se enquadram nos parâmetros do passageiro
    public ArrayList<String> listaCaronas(InterfaceCli referenciaCliente, String origem, String destino, String data) throws RemoteException {

        ArrayList<String> listaCaronas = new ArrayList<>();
        
        interessePassageiro.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){
                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                    listaCaronas.add("\nMotorista: " + c.getNome() + ". Telefone: " + c.getTelefone());
                }
            }
        });

        return listaCaronas;
    }
    
    @Override
    public int interesseEmMotorista(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data, PublicKey pubKey, String mensagem, byte[] assinatura) throws RemoteException {

        try {
            
            //Valida assinatura digital
            if(recebeMensagem(pubKey, mensagem, assinatura)){
                
                interesseMotorista.add(new InteresseCarona(referenciaCliente, nome, telefone, origem, destino, data));
                
                interessePassageiro.forEach(c -> {
                    //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
                    if(c != null){
                        try {
                            if(c.getInterfaceCli() != referenciaCliente && c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                                System.out.println("Interesse M: " + (interesseMotorista.size() - 1));

                                //Chamada bidirecional chamando método do cliente para notificar o motorista
                                c.getInterfaceCli().notificarMotorista("Novo passageiro! Passageiro: " + nome + " Telefone: " + telefone);

                            } else {
                                //"Notifica" motoristas que não se enquadraram nos dados da carona com vazio. Melhorar.
                                c.getInterfaceCli().notificarMotorista("");
                            }
                        } catch (RemoteException ex) {
                                System.out.println("NotificarPassageiro: " + ex.getMessage());
                        }
                    }
                });
                
                System.out.println();
                
                //Código único do intesse é a posição do elemento na matriz, que não muda
                return interesseMotorista.size() - 1;
                
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(ServImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Se a assinatura digital for inválida, retorna uma posição inválida da lista
        return -1;
    }    

    @Override
    public void cancInteresseEmMotorista(int id) throws RemoteException {
        //Cancela o interesse em carona tornando nulos os dados do motorista
        interesseMotorista.set(id, null);
        
    }    
    
    @Override
    public int interesseEmPassageiro(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data, int passageiros, PublicKey pubKey, String mensagem, byte[] assinatura) throws RemoteException {

        try {
            
            //Valida assinatura digital
            if(recebeMensagem(pubKey, mensagem, assinatura)){
                
                interessePassageiro.add(new InteressePassageiro(referenciaCliente, nome, telefone, origem, destino, data, passageiros));
                
                interesseMotorista.forEach(c -> {
                    //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
                    if(c != null){
                        try {
                            if(c.getInterfaceCli() != referenciaCliente && c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                                
                                System.out.println("\ninteresse P: " + (interessePassageiro.size() - 1));
                                
                                //Chamada bidirecional chamando método do cliente para notificar o passageiro
                                c.getInterfaceCli().notificarPassageiro("Nova carona! Motorista: " + nome + " Telefone: " + telefone);

                            } else {
                                //"Notifica" passageiros que não se enquadraram nos dados da carona com vazio. Melhorar.           
                                c.getInterfaceCli().notificarPassageiro("");
                            }
                        } catch (RemoteException ex) {
                            System.out.println("NotificarPassageiro: " + ex.getMessage());
                        }
                    }
                });
                
                System.out.println();
                
                //Código único do intesse é a posição do elemento na matriz, que não muda
                return interessePassageiro.size() - 1;
                
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(ServImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Se a assinatura digital for inválida, retorna uma posição inválida da lista
        return -1;
    }
 
    @Override
    public void cancInteresseEmPassageiro(int id) throws RemoteException {
        //Cancela o interesse em passeiro tornando nulos os dados da carona
        interessePassageiro.set(id, null);
        
    }    
 
    //Adaptado de https://www.devmedia.com.br/como-criar-uma-assinatura-digital-em-java/31287
    public boolean recebeMensagem(PublicKey pubKey, String mensagem, byte[] assinatura) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
       Signature clientSig = Signature.getInstance("DSA");
       clientSig.initVerify(pubKey);
       clientSig.update(mensagem.getBytes());

       if (clientSig.verify(assinatura)) {
           //Mensagem corretamente assinada
          System.out.println("A Mensagem recebida foi assinada corretamente.");
          return true;
       } else {
           //Mensagem não pode ser validada
          System.out.println("A Mensagem recebida NÃO pode ser validada.");
          return false;
       }
   }    
    
}