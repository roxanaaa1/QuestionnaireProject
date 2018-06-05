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

import model.Userquestionnaire;

@ApplicationScoped
@Named
public class UserSeeQuestionnaire {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;
	
	@ManagedProperty(value = "userquestionnaires")
	List<Userquestionnaire> userquestionnaires;


	public List<Userquestionnaire> getUserquestionnaires() {
		TypedQuery<Userquestionnaire> query = em.createNamedQuery("Userquestionnaire.findAll", Userquestionnaire.class);
		userquestionnaires = query.getResultList();

		return userquestionnaires;
	}

	public void setUserquestionnaires(List<Userquestionnaire> userquestionnaires) {
		this.userquestionnaires = userquestionnaires;
	}

	
}
