package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idquestion", updatable = false, nullable = false)
	private Integer idquestion;
	

	private Integer idquestionnaire;

	private String question;

	public Question() {
	}

	public Integer getIdquestion() {
		return this.idquestion;
	}

	public void setIdquestion(Integer idquestion) {
		this.idquestion = idquestion;
	}

	public Integer getIdquestionnaire() {
		return this.idquestionnaire;
	}

	public void setIdquestionnaire(Integer idquestionnaire) {
		this.idquestionnaire = idquestionnaire;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return idquestion.toString();
	}
	@Override
	public boolean equals(Object obj) {
		return ((Question)obj).idquestion == idquestion;
		
	}
	

}