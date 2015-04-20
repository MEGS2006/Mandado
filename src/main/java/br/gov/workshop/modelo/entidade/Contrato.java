/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcio
 */
@Entity
public class Contrato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20,unique = true, nullable = false)
    @NotNull
    private String numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fim;
    @Column(length = 200)
    private String finalidade;
    @Column(length = 16)
    private String numProcesso;
    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private PessoaFisica preposto;
    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private PessoaJuridica contratada;
    
    @OneToMany(mappedBy = "contrato")
    private List<PessoaFisica> terceirizados= new ArrayList<>();
    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private Area area; 

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Long getIdContrato() {
        return id;
    }

    public void setIdContrato(Long idContrato) {
        this.id = idContrato;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public PessoaFisica getPreposto() {
        return preposto;
    }

    public void setPreposto(PessoaFisica preposto) {
        this.preposto = preposto;
    }

    public PessoaJuridica getContratada() {
        return contratada;
    }

    public void setContratada(PessoaJuridica contratada) {
        this.contratada = contratada;
    }

    public List<PessoaFisica> getTerceirizados() {
        return terceirizados;
    }

    public void setTerceirizados(List<PessoaFisica> terceirizados) {
        this.terceirizados =  terceirizados;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
