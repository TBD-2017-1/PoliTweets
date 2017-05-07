package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the partido database table.
 * 
 */
@Entity
@Table(name="partido")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="nombre", nullable=false, unique=true, length=45)
	private String nombre;

	@Column(name="cuenta_twitter", nullable=false, unique=true, length=45)
	private String cuenta_twitter;

	public Partido() {
	}

	public int getId() {
		return this.id;
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

	public String getCuenta_twitter() {
		return cuenta_twitter;
	}

	public void setCuenta_twitter(String cuenta_twitter) {
		this.cuenta_twitter = cuenta_twitter;
	}
	
}