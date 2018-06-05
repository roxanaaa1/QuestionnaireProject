package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Answer;

@FacesConverter(value = "answerConverter")
public class AnswerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String id) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Example");

		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Answer> answerQuery = em.createNamedQuery("Answer.findAll", Answer.class);
		List<Answer> answers = answerQuery.getResultList();
		Answer answer = answers.stream().filter(a -> a.getIdanswer().equals(Integer.valueOf(id))).findFirst().get();

		return answer;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object answer) {
		return ((Answer) answer).toString();
	}
}
