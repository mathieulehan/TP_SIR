package main.java.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.java.fr.ensai.tpjpaensai.domain.Candidat;
import main.java.fr.ensai.tpjpaensai.domain.Department;


public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:hsql:/localhost/ ", "sa",  "");
		EntityManagerFactory factory =   
 			 Persistence.createEntityManagerFactory("anteros");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listEmployees();
			
   	 manager.close();
		System.out.println(".. done");
		// test
		Statement table = connexion.createStatement() ;
		table.executeUpdate("CREATE TABLE test (" + 
			"colonne1 INT , colonne2 INT)");
		// permet de mettre les données sur le disque avant d'éteindre la base
		Statement stop = connexion.createStatement();
		stop.executeQuery("SHUTDOWN");
		stop.close();
		connexion.close();
	}

	private void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Candidat a", Candidat.class).getResultList().size();
		if (numOfEmployees == 0) {
			Department department = new Department("java");
			manager.persist(department);

			manager.persist(new Candidat("Jakab Gipsz",department));
			manager.persist(new Candidat("Captain Nemo",department));

		}
	}

	private void listEmployees() {
		List<Candidat> resultList = manager.createQuery("Select a From Candidat a", Candidat.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Candidat next : resultList) {
			System.out.println("next employee: " + next);
		}
	}

	


}