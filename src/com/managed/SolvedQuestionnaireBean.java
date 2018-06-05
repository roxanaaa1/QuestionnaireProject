package com.managed;

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
public class SolvedQuestionnaireBean {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;
	
	@ManagedProperty(value = "userquestionnaire")
	Userquestionnaire userquestionnaire;	

	@ManagedProperty(value = "selectedanswers")
	Map<Integer, Answer> selectedanswers = new HashMap<>();
	
	@ManagedProperty(value = "questions")
	List<Question> questions;


	public Userquestionnaire getUserquestionnaire() {
		return userquestionnaire;
	}

	public void setUserquestionnaire(Userquestionnaire userquestionnaire) {
		this.userquestionnaire = userquestionnaire;
	}
	
	// get only the questions for the current questionnaire
	public List<Question> getQuestions() {
		TypedQuery<Question> query = em.createNamedQuery("Question.findAll", Question.class);
		questions = query.getResultList();
		questions.removeIf(q -> !q.getIdquestionnaire().equals(userquestionnaire.getQuestionnaire().getIdquestionnaire()));

		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public Map<Integer, Answer> getSelectedanswers() {
		TypedQuery<Selectedanswer> querySelectedAnswer = em.createNamedQuery("Selectedanswer.findAll", Selectedanswer.class);
		List<Selectedanswer> selectedAnswers = querySelectedAnswer.getResultList();
		
		selectedAnswers.removeIf(s-> !s.getIduserquestionnaire().equals(userquestionnaire.getIduserquestionnaire()));
		
		List<Integer> answerIds = selectedAnswers.stream().map(Selectedanswer::getIdanswer).collect(Collectors.toList());

		TypedQuery<Answer> query = em.createNamedQuery("Answer.findAll", Answer.class);
		List<Answer> allanswers = query.getResultList();
		
		allanswers.removeIf(a -> !answerIds.contains(a.getIdanswer()));
		
		this.selectedanswers = allanswers.stream().collect(Collectors.toMap(Answer::getIdquestion, a -> a));
		
		return selectedanswers;
	}

	public void setSelectedanswers(HashMap<Integer, Answer> selectedanswers) {
		this.selectedanswers = selectedanswers;
	}
	



}
