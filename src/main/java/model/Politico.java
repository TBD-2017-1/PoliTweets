package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the politico database table.
 * 
 */
@Entity
@Table(name="politico")
@NamedQuery(name="Politico.findAll", query="SELECT a FROM Politico a")
public class Politico implements Serializable {
	private static final long serialVersionUID = 1L;

	//Atributes
	@Id
	@Column(name="id", nullable=false, unique=true)
	private int id;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;
	
	@Column(name="apellido", nullable=false, length=45)
	private String apellido;

	@Column(name="cuentaTwitter", length=45)
	private String cuentaTwitter;
	
	//Relations
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idconglomerado")
	private Conglomerado conglomerado_politico;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idpartido")
	private Partido partido;

	@JoinTable
	(
		name="politico_keyword",
		joinColumns={ @JoinColumn(name="idpolitico", referencedColumnName="id") },
		inverseJoinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") }
	)
	@OneToMany
	private List<Keyword> keywords;

	//Methods
	public Politico() {
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuentaTwitter() {
		return cuentaTwitter;
	}

	public void setCuentaTwitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}

	public Conglomerado getConglomerado() {
		return conglomerado_politico;
	}

	public void setConglomerado(Conglomerado conglomerado_politico) {
		this.conglomerado_politico = conglomerado_politico;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void addKeyword(Keyword keyword) {
		this.keywords.add(keyword);
	}
}