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

import main.java.fr.ensai.tpjpaensai.domain.Candidat;
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
			Choix choix = new Choix();
			Collection<Choix> choixSondage = new HashSet<Choix>();
			String selectedSurvey = request.getParameter("survey_selector");
			 
		     switch (selectedSurvey) {
			case "sondage_date":
				SondageTypeDate sondage = new SondageTypeDate(title, theme);
				choix.setEnonce(request.getParameter("date"));
				choixSondage.add(choix);
				sondage.setChoix(choixSondage);
				em.persist(choix);
				em.persist(sondage);
				System.out.println("coin");
				break;
			case "sondage_date_lieu":
				
				break;
			case "sondage_lieu":
				
				break;
			case "sondage_liste":
				
				break;
			default:
				break;
			}
			tx.commit();
		} catch (Exception e) {}
	}
}