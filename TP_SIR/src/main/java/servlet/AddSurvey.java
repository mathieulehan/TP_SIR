package main.java.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.istic.sir.rest.Constantes;
import main.java.fr.ensai.tpjpaensai.domain.Choix;
import main.java.fr.ensai.tpjpaensai.domain.Department;
import main.java.fr.ensai.tpjpaensai.domain.Employee;
import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDateEtLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

/**
 * Ajoute un sondage et ses choix en base de données
 * @author mathi
 *
 */
@WebServlet(name="addsurvey",
urlPatterns={"/AddSurvey"})
public class AddSurvey extends HttpServlet{

	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tx;


	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		factory = Persistence.createEntityManagerFactory(Constantes.connexion);
		em = factory.createEntityManager();
		tx = em.getTransaction();
	}

	@Override
	public void destroy() {
		em.close();		
		super.destroy();
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		Sondage sondageCree = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(Constantes.dateFormat);
			tx.begin();
			//Envoie en base
			String title = request.getParameter(Constantes.title);
			String theme = request.getParameter(Constantes.theme);
			Collection<Choix> choixSondage = new HashSet<Choix>();
			String selectedSurvey = request.getParameter("survey_selector");

			switch (selectedSurvey) {
			case "sondage_date":
				Choix choixDate = new Choix();
				SondageTypeDate sondageDate = new SondageTypeDate(title, theme);
				choixDate.setEnonce(request.getParameter(Constantes.date));
				choixSondage.add(choixDate);
				sondageDate.setChoix(choixSondage);
				sondageCree = sondageDate;
				em.persist(choixDate);
				break;
			case "sondage_date_lieu":
				Choix choixDate2 = new Choix();
				Choix choixLieu = new Choix();
				SondageTypeDateEtLieu sondageDateLieu = new SondageTypeDateEtLieu(title, theme);
				choixDate2.setEnonce(request.getParameter(Constantes.date2));
				choixLieu.setEnonce(request.getParameter(Constantes.location));
				choixSondage.add(choixDate2);
				choixSondage.add(choixLieu);
				sondageDateLieu.setChoix(choixSondage);
				sondageCree = sondageDateLieu;
				em.persist(choixDate2);
				em.persist(choixLieu);
				break;
			case "sondage_lieu":
				Choix choixLieu2 = new Choix();
				SondageTypeLieu sondageLieu = new SondageTypeLieu(title, theme);
				choixLieu2.setEnonce(request.getParameter(Constantes.location2));
				choixSondage.add(choixLieu2);
				sondageLieu.setChoix(choixSondage);
				sondageCree = sondageLieu;
				em.persist(choixLieu2);
				break;
			case "sondage_liste":
				SondageTypeListeChoix sondageListe = new SondageTypeListeChoix(title, theme);
				int idChoix = 1;
				// TODO le faire d'une meilleure maniere
				while(request.getParameter("choix_"+idChoix) != null) {
					Choix choixListe = new Choix();
					choixListe.setEnonce(request.getParameter("choix_"+idChoix));
					em.persist(choixListe);
					choixSondage.add(choixListe);
					idChoix++;
				}
				sondageListe.setChoix(choixSondage);
				sondageCree = sondageListe;
				break;
			default:
				break;
			}
			Employee createur = new Employee();
			String name = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			Employee employeeExiste = null;
			try {
				employeeExiste = (Employee) em.createQuery("SELECT e " + "FROM Employee e "+  "WHERE e.lastName = :lastName AND "+ "e.firstName = :name", Employee.class).setParameter("lastName", lastName).setParameter("name", name).getSingleResult();
			} catch (Exception e) {
				System.err.println(e);
			}
			if (employeeExiste != null) {
				sondageCree.addCreateur(employeeExiste);
				em.persist(sondageCree);
				em.persist(employeeExiste);
			}
			else {
				// Dans le cas où l'employé n'existe pas
				Department department = new Department("Département des sondages");
				em.persist(department);
				createur = new Employee(name, lastName, department);
				sondageCree.addCreateur(createur);
				em.persist(sondageCree);
				em.persist(createur);
			}
			tx.commit();
		} catch (Exception e) {}
	}
}