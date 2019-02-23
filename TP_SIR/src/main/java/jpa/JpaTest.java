package main.java.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.java.fr.ensai.tpjpaensai.domain.Candidat;
import main.java.fr.ensai.tpjpaensai.domain.Department;


public class JpaTest {

	private static EntityManager manager;

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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("localhost");
		manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		createEmployees();
		tx.commit();
		listEmployees();
   	 	manager.close();
		System.out.println(".. done");
	}

	private static void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Candidat a", Candidat.class).getResultList().size();
		if (numOfEmployees == 0) {
			Candidat candidat1 = new Candidat("Jakab", "Gipsz");
			candidat1.setDatenaissance(new Date());
			Candidat candidat2 = new Candidat("Captain", "Nemo");
			candidat2.setDatenaissance(new Date());
			manager.persist(candidat1);
			manager.persist(candidat2);
		}
	}

	private static void listEmployees() {
		List<Candidat> resultList = manager.createQuery("Select a From Candidat a", Candidat.class).getResultList();
		System.out.println("num of employees:" + resultList.size());
		for (Candidat next : resultList) {
			System.out.println("next employee: " + next.getLastName());
		}
	}

	


}