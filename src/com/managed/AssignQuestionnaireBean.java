package com.managed;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import model.Questionnaire;
import model.User;
import model.Userquestionnaire;

@ApplicationScoped
@Named
public class AssignQuestionnaireBean {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	@ManagedProperty(value = "questionnaire")
	Questionnaire questionnaire;

	@ManagedProperty(value = "userquestionnaires")
	List<Userquestionnaire> userquestionnaires;

	@ManagedProperty(value = "user")
	private User user;

	@ManagedProperty(value = "questionnaires")
	List<Questionnaire> questionnaires;

	@ManagedProperty(value = "users")
	List<User> users;

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		TypedQuery<User> userQuery = em.createNamedQuery("User.findAll", User.class);
		users = userQuery.getResultList();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Questionnaire> getQuestionnaires() {
		TypedQuery<Questionnaire> query = em.createNamedQuery("Questionnaire.findAll", Questionnaire.class);
		questionnaires = query.getResultList();
		return questionnaires;
	}

	public List<Userquestionnaire> getUserquestionnaires() {
		TypedQuery<Userquestionnaire> query = em.createNamedQuery("Userquestionnaire.findAll", Userquestionnaire.class);
		userquestionnaires = query.getResultList();

		return userquestionnaires;
	}

	public void setUserquestionnaires(List<Userquestionnaire> userquestionnaires) {
		this.userquestionnaires = userquestionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public String assign() {
		Userquestionnaire uq = new Userquestionnaire();

		uq.setQuestionnaire(questionnaire);
		uq.setUser(user);
		uq.setScore(-1);

		try {
			ut.begin();
			em.persist(uq);
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		questionnaire.addUserquestionnaire(uq);

//		String myEmailId = "xyz@gmail.com";
//		String myPassword = "password";
//		String senderId = "xyz@yahoo.com";
//		try {
//			MultiPartEmail email = new MultiPartEmail();
//			email.setSmtpPort(587);
//			email.setAuthenticator(new DefaultAuthenticator(myEmailId, myPassword));
//			email.setDebug(true);
//			email.setHostName("smtp.gmail.com");
//			email.setFrom(myEmailId);
//			email.setSubject("Hi");
//			email.setMsg(
//					"This is a test mail ... :-)\n\nPlease check attachements that I have sent.\n\nThanks,\nFahim");
//			email.addTo(senderId);
//			email.setTLS(true);
//
////			EmailAttachment attachment = new EmailAttachment();
////			attachment.setPath("/Users/fahadparkar/Desktop/Fahim/tables.xlsx");
////			attachment.setDisposition(EmailAttachment.ATTACHMENT);
////			attachment.setDescription("Excel");
////			attachment.setName("tables.xlsx");
////			email.attach(attachment);
//
//			email.send();
//			System.out.println("Mail sent!");
//		} catch (Exception e) {
//			System.out.println("Exception :: " + e);
//		}

		return null;
	}
}
