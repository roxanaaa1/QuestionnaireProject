package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userquestionnaire database table.
 * 
 */
@Entity
@NamedQuery(name="Userquestionnaire.findAll", query="SELECT u FROM Userquestionnaire u")
public class Userquestionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iduserquestionnaire", updatable = false, nullable = false)
	private Integer iduserquestionnaire;

	private double score;

	//bi-directional many-to-one association to Questionnaire
	@ManyToOne
	@JoinColumn(name="idquestionnaire")
	private Questionnaire questionnaire;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User user;

	public Userquestionnaire() {
	}

	public Integer getIduserquestionnaire() {
		return this.iduserquestionnaire;
	}

	public void setIduserquestionnaire(Integer iduserquestionnaire) {
		this.iduserquestionnaire = iduserquestionnaire;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.valueOf(iduserquestionnaire);
	}

	@Override
	public boolean equals(Object obj) {
		return ((Userquestionnaire)obj).getIduserquestionnaire() == iduserquestionnaire;
	}
}