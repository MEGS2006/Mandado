/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcio
 */
@Entity
public class PessoaFisica extends Pessoa{
    //private Long IdPessoaFisica;
    @Column(length = 11,unique = true, nullable = false)
    @NotNull
    private String cpf;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    //@Lob
    private byte[] foto;
    private boolean excluido;
    @ManyToOne
    private Contrato contrato;
    @Enumerated
    private Genero genero;
    
    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
   
    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public byte[] getFoto() {
        return foto;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
    
    
    
    
    
}
