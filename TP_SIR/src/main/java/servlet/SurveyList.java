package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class SurveyList extends HttpServlet{

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
			TypedQuery<Sondage> query = em.createQuery("SELECT c FROM Sondage c", Sondage.class);
			
			List<Sondage> sondages = query.getResultList();
			
			out.println("<HTML>\n<BODY><a href='/'>Retour</a>\n" + 
					"<H1>Sondages disponibles : </H1>\n");
			for (Sondage sondage : sondages) {		
				out.println("<UL>\n" + 
						" <LI>Titre: " + "<a href=\"/AnswerSurvey?id=" + sondage.getId() + "\">" + sondage.getTitre() + "</a>\n" 
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
	
	public List<Sondage> getSondages(){
		TypedQuery<Sondage> sondages = em.createQuery("SELECT c FROM Sondage c", Sondage.class);
		List<Sondage> results = sondages.getResultList();
		return results;
	}
}
