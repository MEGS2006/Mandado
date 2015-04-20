package br.gov.workshop.modelo.bean;

import br.gov.workshop.modelo.dao.AbstractDAO;
import br.gov.workshop.modelo.util.DetectEntity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMB<T, PK> implements Serializable {

    @Inject
    protected AbstractDAO<T, PK> dao;

    private final Class<T> classe;

    public AbstractMB() {
        classe = DetectEntity.getEntityClass(getClass());
    }

    public AbstractDAO<T, PK> getDAO() {
        return dao;
    }
    


    @Inject
    private Mensageiro mensageiro;

    private List<T> lista;
    private T selecao;
    private PK id;

    public T getSelecao() {
        return selecao;
    }

    public List<T> getLista() {
        if (lista == null) {
            lista = getDAO().findAll();
        }
        return lista;
    }
    
    public boolean isNew(){
        return (id == null);
    }

    @Transactional
    public String save() {
        try {
            boolean novo = (id == null);
            if(novo )
                getDAO().salvar(selecao);
            else
                selecao = getDAO().atualizar(selecao);
            //Utilizar flush para que possÃ­veis erros no BD sejam interceptados antes do fim do mÃ©todo
            getDAO().flush();
            mensageiro.addAviso(novo ? "registro inserido com sucesso!" : "registro alterado com sucesso!");
            return "list?faces-redirect=true";
        } catch (Exception e) {
            mensageiro.addMessage(e);
            return "";
        }
    }
    @Transactional
    public String remove() {
       String retorno =remove(selecao);
       selecao=null;
       return retorno;
    }    

    @Transactional
    public String remove(T item) {
        try {
            getDAO().remover(getDAO().atualizar(item));
            //Utilizar flush para que possÃ­veis erros no BD sejam interceptados antes do fim do mÃ©todo
            getDAO().flush();
            selecao = null;
            mensageiro.addAviso("registro excluÃ­do com sucesso!");
            return "list?faces-redirect=true";
        } catch (Exception e) {
            mensageiro.addMessage(e);
            return "";
        }
    }

    @PostConstruct
    protected void init() {
        try {
            selecao = classe.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void setId(PK id) {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            selecao = dao.findById(id);
            this.id = id;
        }
    }

    public PK getId() {
        return id;
    }

}