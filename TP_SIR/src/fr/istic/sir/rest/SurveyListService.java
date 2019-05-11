package fr.istic.sir.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.fr.ensai.tpjpaensai.domain.Choix;
import main.java.fr.ensai.tpjpaensai.domain.Employee;
import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

/**
 * Service gerant les sondages de type liste
 * @author mathi
 *
 */
@Path("/listSurveys")
public class SurveyListService extends AbstractService<SondageTypeListeChoix>{

	private EntityManager em;

	public SurveyListService() {
		super(SondageTypeListeChoix.class);
	} 

	/**
	 * travail en cours
	 * @param json
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	@POST
	@Path("create")
	@Produces({"application/json"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws JSONException, ParseException {
		getEntityManager();
		JSONObject survey = new JSONObject(json);
		SondageTypeListeChoix surveyEntity = new SondageTypeListeChoix();
		surveyEntity.setTitre((survey.getString("titre")));
		surveyEntity.setTheme((survey.getString("theme")));
		List<Choix> choix = new ArrayList<>();
		int count = survey.length();
		for (int i = 0; i < count-3 ; i++) {
			Choix lieu = new Choix();
			lieu.setEnonce(survey.getString("choix_" + i));
			choix.add(lieu);
		}
		surveyEntity.setChoix(choix);
		JSONObject jsonCreateur = new JSONObject(survey.getString("employee"));
		String firstName = jsonCreateur.getString("firstName");
		String lastName = jsonCreateur.getString("lastName");
		Employee createur = null;
		TypedQuery<Employee> query;
		query = em.createQuery("SELECT c FROM Employee c WHERE c.firstName = :firstName AND c.lastName = :lastName" , Employee.class).setParameter("firstName", firstName).setParameter("lastName",  lastName);
		createur = query.getSingleResult();
		surveyEntity.addCreateur(createur);
		return super.create(surveyEntity);
	}
	
	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Sondage find(@PathParam("id") Integer id) {
		return (super.find(id));
	}

	@GET
	@Produces({ "application/json" })
	public List<SondageTypeListeChoix> findAll() {
		return super.findAll();
	}

	@GET
	@Path("count")
	@Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}
	
	protected EntityManager getEntityManager() {
		em = EntitySingleton.getManager();
		return em;
}
}
