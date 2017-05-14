package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the keyword database table.
 * 
 */
@Entity
@Table(name="keyword")
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false, unique=true)
    private int id;

    @Column(name="value", nullable=false, unique=true, length=45)
    private String value;

    @ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable
    (
        name="conglomerado_keyword",
        joinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") },
        inverseJoinColumns={ @JoinColumn(name="idconglomerado", referencedColumnName="id") }
    )
    private List<Conglomerado> conglomerados_keywords;

    @ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable
    (
        name="partido_keyword",
        joinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") },
        inverseJoinColumns={ @JoinColumn(name="idpartido", referencedColumnName="id") }
    )
    private List<Partido> partidos_keywords;

    @ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable
    (
        name="politico_keyword",
        joinColumns={ @JoinColumn(name="idkeyword", referencedColumnName="id") },
        inverseJoinColumns={ @JoinColumn(name="idpolitico", referencedColumnName="id") }
    )
    private List<Politico> politicos_keywords;


    public Keyword() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Conglomerado> getConglomerados(){
        return this.conglomerados_keywords;
    }

    public void addConglomerado(Conglomerado conglomerado){
        this.conglomerados_keywords.add(conglomerado);
    }
    
    public void removeConglomerado(Conglomerado conglomerado){
        this.conglomerados_keywords.remove(conglomerado);
    }

    public List<Partido> getPartidos(){
        return this.partidos_keywords;
    }

    public void addPartido(Partido partido){
        this.partidos_keywords.add(partido);
    }

    public List<Politico> getPoliticos(){
        return this.politicos_keywords;
    }

    public void addPolitico(Politico politicos){
        this.politicos_keywords.add(politicos);
    }
}