package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the conglomerado database table.
 * 
 */
@Entity
@Table(name="conglomerado")
@NamedQuery(name="Conglomerado.findAll", query="SELECT c FROM Conglomerado c")
public class Conglomerado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", nullable=false, unique=true)
	private int id;
	
	@OneToMany(mappedBy="conglomerado")
	private List<Partido> listaPartidos;
	
	@OneToMany(mappedBy="conglomerado")
	private List<Politico> listaPoliticos;
	
	@JoinTable
	(
		name="conglomerado_keyword",
		joinColumns={ @JoinColumn(name="idconglomerado", referencedColumnName="id") },
		inverseJoinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") }
	)
	@OneToMany
	private List<Keyword> keywords;

	@Column(name="nombre", nullable=false, unique=true, length=45)
	private String nombre;

	@Column(name="cuentaTwitter", nullable=false, unique=true, length=45)
	private String cuentaTwitter;

	public Conglomerado() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Partido> getListaPartidos() {
		return listaPartidos;
	}

	public void setListaPartidos(List<Partido> listaPartidos) {
		this.listaPartidos = listaPartidos;
	}

	public List<Politico> getListaPoliticos() {
		return listaPoliticos;
	}

	public void setListaPoliticos(List<Politico> listaPoliticos) {
		this.listaPoliticos = listaPoliticos;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuentaTwitter() {
		return cuentaTwitter;
	}

	public void setCuentaTwitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}
	
}