/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.dao;

import br.gov.workshop.modelo.entidade.Pessoa;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Marcio
 */
@Stateless
public class PessoaDAO extends AbstractDAO<Pessoa, Long> implements Serializable {
    
}
