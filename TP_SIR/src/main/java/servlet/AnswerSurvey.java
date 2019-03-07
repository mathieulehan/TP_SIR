package main.java.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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

/**
 * Cree le formulaire permettant de repondre a un sondage
 * Et appelle le servlet AddAnswer qui enregistre la reponse
 * @author mathi
 *
 */
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
				+ "<FORM Method=\"POST\" Action=\"/AddAnswer\">\r\n" + 
				"Nom :         <INPUT type=text size=20 name=lastName><BR>\r\n" + 
				"Prénom :     <INPUT type=text size=20 name=firstName><BR>\r\n"
				+ sondageChoisi.getTitre());

		Collection<Choix> choixSondage = sondageChoisi.getChoix();
		for (Choix choix : choixSondage) {
			out.println(" <input type=\"checkbox\" name=\"choix\" value=\"" + choix.getId() + "\" />" + choix.getEnonce() + "<br />");
		}
		out.println(
				"<br><INPUT type=submit value=Répondre></FORM>\r\n");
	}

	public Sondage getSondage(HttpServletRequest request) {
		factory = Persistence.createEntityManagerFactory("localhost");
		em = factory.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		int surveyId = Integer.parseInt(request.getParameter("id"));
		TypedQuery<Sondage> query;

		Sondage sondageChoisi = null;

		query = em.createQuery("SELECT c FROM Sondage c WHERE c.id = " + surveyId , Sondage.class);
		sondageChoisi = query.getSingleResult();
		return sondageChoisi;
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

	}
}
