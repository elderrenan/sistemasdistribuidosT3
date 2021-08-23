/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

/**
 *
 * @author elder
 */
public class InteressePassageiro {

    private final String nome;
    private final String telefone;
    private final String origem;
    private final String destino;
    private final String data;
    private final int passageiros;
    private final InterfaceCli referenciaCliente;
    
    public InteressePassageiro(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data, int passageiros) {
        this.referenciaCliente = referenciaCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.passageiros = passageiros;
    }

    public InterfaceCli getInterfaceCli() {
        return referenciaCliente;
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
    
}