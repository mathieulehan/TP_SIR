package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.ensai.tpjpaensai.domain.Employee;
import main.java.fr.ensai.tpjpaensai.domain.Choix;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDateEtLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

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
		factory = Persistence.createEntityManagerFactory("localhost");
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

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			tx.begin();
			//Envoie en base
			String title = request.getParameter("title");
			String theme = request.getParameter("theme");
			Collection<Choix> choixSondage = new HashSet<Choix>();
			String selectedSurvey = request.getParameter("survey_selector");
			 
		     switch (selectedSurvey) {
			case "sondage_date":
				Choix choixDate = new Choix();
				SondageTypeDate sondageDate = new SondageTypeDate(title, theme);
				choixDate.setEnonce(request.getParameter("date"));
				choixSondage.add(choixDate);
				sondageDate.setChoix(choixSondage);
				em.persist(choixDate);
				em.persist(sondageDate);
				break;
			case "sondage_date_lieu":
				Choix choixDate2 = new Choix();
				Choix choixLieu = new Choix();
				SondageTypeDateEtLieu sondageDateLieu = new SondageTypeDateEtLieu(title, theme);
				choixDate2.setEnonce(request.getParameter("date2"));
				choixLieu.setEnonce(request.getParameter("location"));
				choixSondage.add(choixDate2);
				choixSondage.add(choixLieu);
				sondageDateLieu.setChoix(choixSondage);
				em.persist(choixDate2);
				em.persist(choixLieu);
				em.persist(sondageDateLieu);
				break;
			case "sondage_lieu":
				Choix choixLieu2 = new Choix();
				SondageTypeLieu sondageLieu = new SondageTypeLieu(title, theme);
				choixLieu2.setEnonce(request.getParameter("location2"));
				choixSondage.add(choixLieu2);
				sondageLieu.setChoix(choixSondage);
				em.persist(choixLieu2);
				em.persist(sondageLieu);
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
				em.persist(sondageListe);
				break;
			default:
				break;
			}
			tx.commit();
		} catch (Exception e) {}
	}
}