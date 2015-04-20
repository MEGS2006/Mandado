/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.dao;

import br.gov.workshop.modelo.entidade.Area;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Marcio
 */
public class DaoArea implements Serializable {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
   
    private EntityManager em;
    
    public void salvar(Area area) {

        em.persist(area);

    }

    public void remover(Area area) {
        em.remove(em.merge(area));
    }

    
    
    public Area atualizar(Area area) {
        return em.merge(area);
    }
    
    
    public List<Area> findAll(){
        return em.createQuery("select c from Area c order by c.nome", Area.class).getResultList();
    }
    
    public Area findById(Long id){
        return em.createQuery("select c from Area c where c.id =:id", Area.class).setParameter("id",id).getSingleResult();
    }
    
}
