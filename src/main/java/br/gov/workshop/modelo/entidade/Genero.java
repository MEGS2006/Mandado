/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.entidade;

/**
 *
 * @author Marcio
 */

public enum Genero {
    MASC("Masculino"),FEM("Feminino");
    private final String descricao;
    
    private Genero(String descricao){
        this.descricao = descricao;
    }
 public String getDescricao(){
    return descricao;
}
    
    
}
