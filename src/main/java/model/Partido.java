package model;

import java.io.Serializable;
import java.util.List;
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idconglomerado")
	private Conglomerado conglomerado;
	
	@OneToMany(mappedBy="partido")
	private List<Politico> listaPoliticos;
	
	@Column(name="nombre", nullable=false, unique=true, length=45)
	private String nombre;

	@Column(name="cuentaTwitter", nullable=false, unique=true, length=45)
	private String cuentaTwitter;

	public Partido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conglomerado getConglomerado() {
		return conglomerado;
	}

	public void setConglomerado(Conglomerado conglomerado) {
		this.conglomerado = conglomerado;
	}

	public List<Politico> getListaPoliticos() {
		return listaPoliticos;
	}

	public void setListaPoliticos(List<Politico> listaPoliticos) {
		this.listaPoliticos = listaPoliticos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuenta_twitter() {
		return cuentaTwitter;
	}

	public void setCuenta_twitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}
	
}