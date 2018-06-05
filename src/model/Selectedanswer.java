package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the selectedanswer database table.
 * 
 */
@Entity
@NamedQuery(name="Selectedanswer.findAll", query="SELECT s FROM Selectedanswer s")
public class Selectedanswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idselectedanswer;

	private Integer idanswer;

	private Integer iduserquestionnaire;

	public Selectedanswer() {
	}

	public Integer getIdselectedanswer() {
		return this.idselectedanswer;
	}

	public void setIdselectedanswer(Integer idselectedanswer) {
		this.idselectedanswer = idselectedanswer;
	}

	public Integer getIdanswer() {
		return this.idanswer;
	}

	public void setIdanswer(Integer idanswer) {
		this.idanswer = idanswer;
	}

	public Integer getIduserquestionnaire() {
		return this.iduserquestionnaire;
	}

	public void setIduserquestionnaire(Integer iduserquestionnaire) {
		this.iduserquestionnaire = iduserquestionnaire;
	}

	@Override
	public String toString() {
		return idselectedanswer.toString();
	}

}