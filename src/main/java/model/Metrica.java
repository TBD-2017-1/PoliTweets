package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the metrica database table.
 * 
 */
@Entity
@Table(name="metrica")
@NamedQuery(name="Metrica.findAll", query="SELECT m FROM Metrica m")
public class Metrica implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id", unique=true, nullable=false)
    private int id;
    
    @Column(name="nombre", nullable=false, length=45)
    private String nombre;
    
    public Metrica() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}