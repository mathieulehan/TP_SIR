package main.java.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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

@WebServlet(name="userinfo",
urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {
	
	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tx;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		factory = Persistence.createEntityManagerFactory("mysql");
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
		Employee employee = new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setDatenaissance(format.parse(request.getParameter("date")));
		em.persist(employee);
		tx.commit();
} catch (Exception e) {}
    PrintWriter out = response.getWriter();

    
    out.println("<HTML>\n<BODY>\n" +
                "<a href='/'>Retour</a><br><H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +            
        " <LI>Nom: "
                + request.getParameter("lastname") + "\n" +
                " <LI>Prenom: "
                + request.getParameter("firstname") + "\n" +
                " <LI>Age: "
                + request.getParameter("date") + "\n" +
                "</UL>\n" +                
        "</BODY></HTML>");
}
}
