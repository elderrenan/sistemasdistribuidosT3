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
public class InteresseCarona {
    
    private final String nome;
    private final String telefone;
    private final String origem;
    private final String destino;
    private final String data;
    private final InterfaceCli referenciaCliente;

    public InteresseCarona(InterfaceCli referenciaCliente, String nome, String telefone, String origem, String destino, String data) {
        this.referenciaCliente = referenciaCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
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
      
}