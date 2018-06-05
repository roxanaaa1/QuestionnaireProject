package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questionnaire database table.
 * 
 */
@Entity
@NamedQuery(name="Questionnaire.findAll", query="SELECT q FROM Questionnaire q")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idquestionnaire", updatable = false, nullable = false)
	private Integer idquestionnaire;

	private String nume;

	//bi-directional many-to-one association to Userquestionnaire
	@OneToMany(mappedBy="questionnaire")
	private List<Userquestionnaire> userquestionnaires;

	public Questionnaire() {
	}

	public Integer getIdquestionnaire() {
		return this.idquestionnaire;
	}

	public void setIdquestionnaire(Integer idquestionnaire) {
		this.idquestionnaire = idquestionnaire;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public List<Userquestionnaire> getUserquestionnaires() {
		return this.userquestionnaires;
	}

	public void setUserquestionnaires(List<Userquestionnaire> userquestionnaires) {
		this.userquestionnaires = userquestionnaires;
	}

	public Userquestionnaire addUserquestionnaire(Userquestionnaire userquestionnaire) {
		getUserquestionnaires().add(userquestionnaire);
		userquestionnaire.setQuestionnaire(this);

		return userquestionnaire;
	}

	public Userquestionnaire removeUserquestionnaire(Userquestionnaire userquestionnaire) {
		getUserquestionnaires().remove(userquestionnaire);
		userquestionnaire.setQuestionnaire(null);

		return userquestionnaire;
	}

	@Override
	public String toString() {
		return String.valueOf(idquestionnaire);
	}

	@Override
	public boolean equals(Object obj) {
		return ((Questionnaire)obj).idquestionnaire == idquestionnaire;
	}
	
}