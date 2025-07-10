

package model;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente {
    
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private LocalDate dataNascimento;
    
    /**
     * @return the id
     */
 public Cliente(String nome, String telefone,String endereco,LocalDate dataNascimento){
     this.nome=nome;
     this.telefone=telefone;
     this.endereco=endereco;
     this.dataNascimento=dataNascimento;
 }
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
     public Cliente() {
    }
   
}
