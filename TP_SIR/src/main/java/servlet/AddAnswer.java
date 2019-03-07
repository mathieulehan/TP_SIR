package main.java.servlet;
import java.io.IOException;

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
import main.java.fr.ensai.tpjpaensai.domain.ReponseUtilisateur;

/**
 * Enregistre la reponse ou les reponses d'un utilisateur a un sondage
 * @author mathi
 *
 */
@WebServlet(name="addAnswer",
urlPatterns={"/AddAnswer"})
public class AddAnswer extends HttpServlet {

	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tx;

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		String userFirstName = request.getParameter("firstName");
		String userLastName = request.getParameter("lastName");
		String[] reponses = request.getParameterValues("choix");
		for (String string : reponses) {
			factory = Persistence.createEntityManagerFactory("localhost");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			int choixId = Integer.parseInt(string);
			TypedQuery<Choix> query;
			query = em.createQuery("SELECT c FROM Choix c WHERE c.id = " + choixId , Choix.class);
			ReponseUtilisateur reponse = new ReponseUtilisateur(userFirstName, userLastName);
			Choix choix = query.getSingleResult();
			reponse.setChoix(choix);
			em.persist(reponse);
			tx.commit();
		}
	}
}
