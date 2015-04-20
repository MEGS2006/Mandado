/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.workshop.modelo.entidade;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marcio
 */
@Entity
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String nome;
    @Column(length = 2)
    private String uf;
    @Column(columnDefinition = "geometry(Point,4326)")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point sede;
    @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon poligono;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Municipio other = (Municipio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Point getSede() {
        return sede;
    }

    public void setSede(Point sede) {
        this.sede = sede;
    }

    public MultiPolygon getPoligono() {
        return poligono;
    }

    public void setPoligono(MultiPolygon poligono) {
        this.poligono = poligono;
    }



}
