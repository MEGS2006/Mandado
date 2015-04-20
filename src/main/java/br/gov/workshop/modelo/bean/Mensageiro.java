/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author jcarlos
 */
@Named
public class Mensageiro implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addMessage(Throwable e) {
        exibe(e);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, getCausa(e), e.toString());
        addMessage(fm);
    }

    public void addAviso(String mensagem) {
        FacesMessage fm = new FacesMessage(mensagem);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);;
        addMessage(fm);
    }

    public void addMessage(FacesMessage fm) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Flash flash = fc.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        fc.addMessage(null, fm);
        if (fm.getSeverity() == FacesMessage.SEVERITY_ERROR || fm.getSeverity() == FacesMessage.SEVERITY_FATAL) {
            fc.validationFailed();
        }
    }

    public void addMessage(Severity severity, String detail, String summary) {
        FacesMessage fm = new FacesMessage(severity, summary, detail);
        addMessage(fm);
    }

    private String trunca(String msg) {
        if (msg != null && msg.length() > 100) {
            return msg.substring(0, 100);
        }
        if (msg == null) {
            return "";
        }
        return msg;
    }

    private void exibe(Throwable e) {
        Logger.getLogger("DPRF").log(Level.SEVERE, "erro", e);
    }

    public String getCausa(Throwable e) {
        if (e.getCause() == null) {
            if (e instanceof ConstraintViolationException) {
                ConstraintViolationException cv = (ConstraintViolationException) e;
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation x : cv.getConstraintViolations()) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(x.getMessage());
                }
                return trunca(sb.toString());
            }
            return trunca(e.getMessage());
        }
        return getCausa(e.getCause());
    }
}