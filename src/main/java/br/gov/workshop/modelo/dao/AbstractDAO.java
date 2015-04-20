package br.gov.workshop.modelo.dao;

import br.gov.workshop.modelo.util.DetectEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

public abstract class AbstractDAO<T, PK> implements Serializable {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)

    protected EntityManager em;
    private final Class<T> classe;

    public AbstractDAO() {
        this.classe = DetectEntity.getEntityClass(getClass());
    }

    public void salvar(T entity) {

        em.persist(entity);

    }

    public void remover(T entity) {
        em.remove(em.merge(entity));
    }

    public T atualizar(T entity) {

        return em.merge(entity);
    }

    public List<T> findAll() {
        String sql = "select c from " + classe.getSimpleName() + " c";
        return em.createQuery(sql, classe).getResultList();
    }

    public T findById(PK id) {
        return em.find(classe, id);
    }

    protected List<T> findByOQL(String oql, Object... params) {
        TypedQuery<T> qry = em.createQuery(oql, classe);
        int x = 1;
        for (Object o : params) {
            qry.setParameter(x++, o);
        }
        return qry.getResultList();
    }
    
    protected T findByOQLUnique(String oql, Object... params) {
        try {
            TypedQuery<T> qry = em.createQuery(oql, classe);
        int x = 1;
        for (Object o : params) {
            qry.setParameter(x++, o);
        }
        qry.setMaxResults(1);
        return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        
    }
    public void flush(){
        em.flush();
    }
}
