package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the conglomerado database table.
 * 
 */
@Entity
@Table(name="partido_metrica")
@NamedQuery(name="PartidoMetrica.findAll", query="SELECT pm FROM PartidoMetrica pm")
public class PartidoMetrica implements Serializable {
    private static final long serialVersionUID = 1L;

    //Atributes
    @Id
    @Column(name="id", nullable=false, unique=true)
    private int id;

    @Column(name="valor")
    private float valor;

    @Temporal(TemporalType.DATE)
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="lugar", length=50)
    private String lugar;

    //Relations

    //Methods
    public PartidoMetrica() {
    }
	
}