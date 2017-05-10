package model;

import java.io.Serializable;
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
	
}