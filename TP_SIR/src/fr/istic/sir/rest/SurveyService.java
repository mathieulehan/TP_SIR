package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDateEtLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

/**
 * Service gerant les tous les types de sondage
 * @author mathi
 *
 */
@Path("/surveys")
public class SurveyService extends AbstractService<Sondage>{

	private EntityManager em;
	
	public SurveyService() {
		super(Sondage.class);
	} 

/*
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
*/
	
	@GET
	@Produces({ "application/json" })
	public List<Sondage> findAll() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constantes.connexion);
		em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx = em.getTransaction();
		tx.begin();
		TypedQuery<SondageTypeDate> date = em.createQuery("SELECT c FROM SondageTypeDate c", SondageTypeDate.class);
		TypedQuery<SondageTypeDateEtLieu> date_lieu = em.createQuery("SELECT c FROM SondageTypeDateEtLieu c", SondageTypeDateEtLieu.class);
		TypedQuery<SondageTypeLieu> lieu = em.createQuery("SELECT c FROM SondageTypeLieu c", SondageTypeLieu.class);
		TypedQuery<SondageTypeListeChoix> liste = em.createQuery("SELECT c FROM SondageTypeListeChoix c", SondageTypeListeChoix.class);

		List<SondageTypeDate> resultsDate = date.getResultList();
		List<SondageTypeDateEtLieu> resultsDateLieu = date_lieu.getResultList();
		List<SondageTypeLieu> resultsLieu = lieu.getResultList();
		List<SondageTypeListeChoix> resultsListe = liste.getResultList();
		List<Sondage> resultAll = new ArrayList<>();

		resultAll.addAll(resultsDate);
		resultAll.addAll(resultsDateLieu);
		resultAll.addAll(resultsLieu);
		resultAll.addAll(resultsListe);
		tx.commit();
		return resultAll;
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
