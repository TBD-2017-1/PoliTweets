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
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
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
	
	@JoinTable
	(
		name="partido_keyword",
		joinColumns={ @JoinColumn(name="idpartido", referencedColumnName="id") },
		inverseJoinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") }
	)
	@OneToMany
	private List<Keyword> keywords;
	
	@Column(name="nombre", nullable=false, unique=true, length=45)
	private String nombre;

	@Column(name="cuentaTwitter", nullable=false, unique=true, length=45)
	private String cuentaTwitter;

	public Partido() {
	}

	public int getId() {
		return id;
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