package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the conglomerado database table.
 * 
 */
@Entity
@Table(name="conglomerado")
@NamedQuery(name="Conglomerado.findAll", query="SELECT c FROM conglomerado c")
public class Conglomerado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="nombre", nullable=false, unique=true, length=45)
	private String nombre;

	@Column(name="cuenta_twitter", nullable=false, unique=true, length=45)
	private String cuenta_twitter;

	public Conglomerado() {
	}

	public int getConglomeradoId() {
		return this.id;
	}

	public void setConglomeradoId(int id) {
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