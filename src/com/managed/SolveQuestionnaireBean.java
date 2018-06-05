package com.managed;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import model.Answer;
import model.Question;
import model.Selectedanswer;
import model.Userquestionnaire;

@ApplicationScoped
@Named
public class SolveQuestionnaireBean {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	@ManagedProperty(value = "questions")
	List<Question> questions;

	List<Answer> allanswers;

	@ManagedProperty(value = "selectedanswers")
	HashMap<Question, Answer> selectedanswers = new HashMap<Question, Answer>();

	@ManagedProperty(value = "answers")
	Map<Integer, List<Answer>> answers = new HashMap<>();

	@ManagedProperty(value = "user")
	Userquestionnaire userquestionnaire;

	public Userquestionnaire getUserquestionnaire() {
		return userquestionnaire;
	}

	public void setUserquestionnaire(Userquestionnaire userquestionnaire) {
		this.userquestionnaire = userquestionnaire;
	}

	public Map<Integer, List<Answer>> getAnswers() {
		TypedQuery<Answer> query = em.createNamedQuery("Answer.findAll", Answer.class);
		allanswers = query.getResultList();
		List<Integer> questionsIds = questions.stream().map(Question::getIdquestion).collect(Collectors.toList());

		allanswers.removeIf(a -> !questionsIds.contains(a.getIdquestion()));
		Collections.shuffle(allanswers);
		
		answers = allanswers.stream().collect(Collectors.groupingBy(Answer::getIdquestion));
		
		return answers;
	}

	public void setAnswers(Map<Integer, List<Answer>> answers) {
		this.answers = answers;
	}

	// get only the questions for the current questionnaire
	public List<Question> getQuestions() {
		TypedQuery<Question> query = em.createNamedQuery("Question.findAll", Question.class);
		questions = query.getResultList();
		questions.removeIf(
				q -> !q.getIdquestionnaire().equals(userquestionnaire.getQuestionnaire().getIdquestionnaire()));

		Collections.reverse(questions);
		//Collections.shuffle(questions);
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public HashMap<Question, Answer> getSelectedanswers() {
		return selectedanswers;
	}

	public void setSelectedanswers(HashMap<Question, Answer> selectedanswers) {
		this.selectedanswers = selectedanswers;
	}

	public String submit() {
		int score = 0;
		for (Answer answer : selectedanswers.values()) {
			score += answer.getCorrect();

			Selectedanswer selectedAnswer = new Selectedanswer();
			selectedAnswer.setIdanswer(answer.getIdanswer());
			selectedAnswer.setIduserquestionnaire(userquestionnaire.getIduserquestionnaire());

			userquestionnaire.setScore(score);
			try {
				ut.begin();
				em.persist(selectedAnswer);
				em.merge(userquestionnaire);
				ut.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		 
	      
		return "user";

	}

}
