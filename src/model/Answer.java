package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idanswer", updatable = false, nullable = false)
	private Integer idanswer;

	private String answer;

	private int correct;

	private Integer idquestion;

	public Answer() {
	}

	public Integer getIdanswer() {
		return this.idanswer;
	}

	public void setIdanswer(Integer idanswer) {
		this.idanswer = idanswer;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCorrect() {
		return this.correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public Integer getIdquestion() {
		return this.idquestion;
	}

	public void setIdquestion(Integer idquestion) {
		this.idquestion = idquestion;
	}

	@Override
	public String toString() {
		return idanswer.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Answer)obj).idanswer == idanswer;
		
	}
	

}