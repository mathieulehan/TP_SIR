package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;

/**
 * Service gerant les sondages de type date
 * @author mathi
 *
 */
@Path("/dateSurveys")
public class SurveyDateService extends AbstractService<SondageTypeDate>{

	private EntityManager em;

	public SurveyDateService() {
		super(SondageTypeDate.class);
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
	public List<SondageTypeDate> findAll() {
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
