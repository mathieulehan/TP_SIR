package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDateEtLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

@WebServlet(name = "surveylist", urlPatterns = { "/SurveyList" })
public class SurveyList<T> extends HttpServlet{

	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tx;

	@Override
	public void init() throws ServletException {
		//		System.out.println("init list");
		//		factory = Persistence.createEntityManagerFactory("example");
		//		manager = factory.createEntityManager();
		factory = Persistence.createEntityManagerFactory("localhost");
		em = factory.createEntityManager();
		tx = em.getTransaction();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		tx.begin();
		try {
			PrintWriter out = response.getWriter();
			TypedQuery<SondageTypeDate> date = em.createQuery("SELECT c FROM SondageTypeDate c", SondageTypeDate.class);
			TypedQuery<SondageTypeDateEtLieu> date_lieu = em.createQuery("SELECT c FROM SondageTypeDateEtLieu c", SondageTypeDateEtLieu.class);
			TypedQuery<SondageTypeLieu> lieu = em.createQuery("SELECT c FROM SondageTypeLieu c", SondageTypeLieu.class);
			TypedQuery<SondageTypeListeChoix> liste = em.createQuery("SELECT c FROM SondageTypeListeChoix c", SondageTypeListeChoix.class);
			
			List<SondageTypeDate> resultsDate = date.getResultList();
			List<SondageTypeDateEtLieu> resultsDateLieu = date_lieu.getResultList();
			List<SondageTypeLieu> resultsLieu = lieu.getResultList();
			List<SondageTypeListeChoix> resultsListe = liste.getResultList();

			out.println("<HTML>\n<BODY><a href='/'>Retour</a>\n" + 
					"<H1>Sondages disponibles : </H1>\n");
			out.println("<H2>Sondages concernant une date : </H2>\n");
			for (Sondage sondage : resultsDate) {		
				out.println("<UL>\n" + 
						" <LI>Titre: " + sondage.getTitre() + "\n" 
						+ " <LI>Theme: " + sondage.getTheme() + "\n" 
						+ "</UL>\n");
			}
			out.println("<H2>Sondages concernant une date et un lieu : </H2>\n");
			for (Sondage sondage : resultsDateLieu) {
				out.println("<UL>\n" + 
						" <LI>Titre: " + sondage.getTitre() + "\n" 
						+ " <LI>Theme: " + sondage.getTheme() + "\n" 
						+ "</UL>\n");
			}
			out.println("<H2>Sondages concernant un lieu: </H2>\n");
			for (Sondage sondage : resultsLieu) {	
				out.println("<UL>\n" + 
						" <LI>Titre: " + sondage.getTitre() + "\n" 
						+ " <LI>Theme: " + sondage.getTheme() + "\n" 
						+ "</UL>\n");
			}
			out.println("<H2>Sondages concernant une liste de choix définis </H2>\n");
			for (Sondage sondage : resultsListe) {
				out.println("<UL>\n" + 
						" <LI>Titre: " + sondage.getTitre() + "\n" 
						+ " <LI>Theme: " + sondage.getTheme() + "\n" 
						+ "</UL>\n");
			}
			out.println("</BODY></HTML>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
	}

	@Override
	public void destroy() {
		em.close();
		super.destroy();
	}
}
