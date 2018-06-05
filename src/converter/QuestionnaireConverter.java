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

import model.Questionnaire;

@FacesConverter(value = "questionnaireConverter")
public class QuestionnaireConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String id) {
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Example" );
        
        EntityManager em = emfactory.createEntityManager( );
    	TypedQuery<Questionnaire> questionnaireQuery = em.createNamedQuery("Questionnaire.findAll", Questionnaire.class);
		List<Questionnaire> questionnaires = questionnaireQuery.getResultList();

		int idValue = Integer.valueOf(id);
		for(Questionnaire q: questionnaires) {
			if(q.getIdquestionnaire().equals(idValue))
				return q;
		}
		
		//Questionnaire questionnaire = questionnaires.stream().filter(q -> q.getIdquestionnaire() == Integer.parseInt(id)).findFirst().get();
		
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object questionnaire) {
        return ((Questionnaire)questionnaire).toString();
    }
}
