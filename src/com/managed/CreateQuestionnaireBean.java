package com.managed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import model.Answer;
import model.Question;
import model.Questionnaire;

@ApplicationScoped
@Named
public class CreateQuestionnaireBean {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	@ManagedProperty(value = "questions")
	List<Question> questions = new ArrayList<>();

	@ManagedProperty(value = "answers")
	HashMap<Integer, ArrayList<Answer>> answers = new HashMap<>();

	@ManagedProperty(value = "questionnaire")
	Questionnaire questionnaire;

	@ManagedProperty(value = "selectedanswers")
	HashMap<Question, Answer> selectedanswers = new HashMap<Question, Answer>();

	@ManagedProperty(value = "message")
	String message;

	public CreateQuestionnaireBean() {
		questionnaire = new Questionnaire();
		questionnaire.setIdquestionnaire(1);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public HashMap<Integer, ArrayList<Answer>> getAnswers() {
		return answers;
	}

	public void setAnswers(HashMap<Integer, ArrayList<Answer>> answers) {
		this.answers = answers;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<Question, Answer> getSelectedanswers() {
		return selectedanswers;
	}

	public void setSelectedanswers(HashMap<Question, Answer> selectedanswers) {
		this.selectedanswers = selectedanswers;
	}

	public String addQuestion() {
		Question question = new Question();

		question.setIdquestionnaire(questionnaire.getIdquestionnaire());
		question.setIdquestion(questions.size() + 1);

		questions.add(question);

		addAnswer(question);
		addAnswer(question);
		addAnswer(question);
		addAnswer(question);

		return null;
	}

	public String addAnswer(Question question) {
		Answer answer = new Answer();

		answer.setIdquestion(question.getIdquestion());
		answer.setIdanswer(answers.size() + 1);

		ArrayList<Answer> currentAnswers;

		if (answers.containsKey(question.getIdquestion())) {
			answer.setIdanswer(answers.get(question.getIdquestion()).size() + 1);
			currentAnswers = answers.get(question.getIdquestion());
		} else {
			answer.setIdanswer(1);
			currentAnswers = new ArrayList<>();

		}

		currentAnswers.add(answer);
		answers.put(question.getIdquestion(), currentAnswers);

		return null;
	}

	public String submit() {

		message = "Succesfully created questionnaire";
		questionnaire.setIdquestionnaire(null);

		try {
			ut.begin();
			em.persist(questionnaire);
			em.flush();
			ut.commit();

			for (Question q : questions) {
				int idQuestion = q.getIdquestion();
				q.setIdquestionnaire(questionnaire.getIdquestionnaire());

				q.setIdquestion(null);

				ut.begin();
				em.persist(q);
				em.flush();
				ut.commit();

				answers.get(idQuestion).get(0).setCorrect(1);
				
				for (Answer a : answers.get(idQuestion)) {
					a.setIdquestion(q.getIdquestion());
					a.setIdanswer(null);

//					if (selectedanswers.containsValue(a))
//						a.setCorrect(1);

					ut.begin();
					em.persist(a);
					em.flush();
					ut.commit();
				}
			}
			
		} catch (Exception e) {
			message = "Could't create questionnaire";
			e.printStackTrace();
		}

		questionnaire = new Questionnaire();
		answers = new HashMap<>();
		questions = new ArrayList<>();

		return null;
	}

	public String cancel() {

		questionnaire = new Questionnaire();

		answers = new HashMap<>();

		questions = new ArrayList<>();

		message = "";

		return "admin";
	}
}
