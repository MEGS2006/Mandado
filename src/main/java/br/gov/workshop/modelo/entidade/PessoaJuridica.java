/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Marcio
 */
@Entity
public class PessoaJuridica extends Pessoa{
    //private Long IdPessoaJuridica;
    @Column(length = 14)
    private String cnpj;
    //@OneToOne
   // private Contrato contrato;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
    
}
