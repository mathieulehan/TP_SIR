package main.java.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.ensai.tpjpaensai.domain.Choix;
import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDate;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeDateEtLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeLieu;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

@WebServlet(name="answerSurvey",
urlPatterns={"/AnswerSurvey"})
public class AnswerSurvey extends HttpServlet {

	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tx;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Sondage sondageChoisi = getSondage(request);
		
		PrintWriter out = response.getWriter();

		out.println("<HTML><head><meta charset=\"utf-8\"/></head>\n<BODY>\n"
				+ "<FORM Method=\"POST\" Action=\"/UserInfo\">\r\n" + 
				"Nom :         <INPUT type=text size=20 name=lastname><BR>\r\n" + 
				"Prénom :     <INPUT type=text size=20 name=firstname><BR>\r\n" + 
				"</FORM>"
				+ sondageChoisi.getTitre());
		
		Collection<Choix> choixSondage = sondageChoisi.getChoix();
		for (Choix choix : choixSondage) {
			out.println("<input type=\"checkbox\">" + choix.getEnonce());
		}
		
		out.println(
				"<br><INPUT type=submit value=Répondre>\r\n");
	}

	public Sondage getSondage(HttpServletRequest request) {
		factory = Persistence.createEntityManagerFactory("mysql");
		em = factory.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		String surveyType = request.getParameter("type");
		int surveyId = Integer.parseInt(request.getParameter("id"));
		TypedQuery<SondageTypeDate> date;
		TypedQuery<SondageTypeDateEtLieu> datelieu;
		TypedQuery<SondageTypeLieu> lieu;
		TypedQuery<SondageTypeListeChoix> liste;
		Sondage sondageChoisi = null;
		switch (surveyType) {
		case "date":
			date = em.createQuery("SELECT c FROM SondageTypeDate c WHERE c.id = " + surveyId , SondageTypeDate.class);
			sondageChoisi = date.getSingleResult();
			break;
		case "datelieu":
			datelieu = em.createQuery("SELECT c FROM SondageTypeDateEtLieu c WHERE c.id = " + surveyId , SondageTypeDateEtLieu.class);
			sondageChoisi = datelieu.getSingleResult();
			break;
		case "lieu":
			lieu = em.createQuery("SELECT c FROM SondageTypeLieu c WHERE c.id = " + surveyId , SondageTypeLieu.class);
			sondageChoisi = lieu.getSingleResult();
			break;
		case "liste":
			liste = em.createQuery("SELECT c FROM SondageTypeListeChoix c WHERE c.id = " + surveyId , SondageTypeListeChoix.class);
			sondageChoisi = liste.getSingleResult();
			break;

		default:
			break;
		}
		return sondageChoisi;
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
	
	}
}
