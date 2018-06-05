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

import model.User;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

	
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String id) {
    	
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Example" );
        
        EntityManager em = emfactory.createEntityManager( );
    	TypedQuery<User> userQuery = em.createNamedQuery("User.findAll", User.class);
		List<User> users = userQuery.getResultList();

		User user = users.stream().filter(u -> u.getIdusers().equals(Integer.valueOf(id))).findFirst().get();
	
        return user;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object user) {
        return ((User)user).toString();
    }

}