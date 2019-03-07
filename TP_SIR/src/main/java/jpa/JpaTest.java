package main.java.jpa;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.sir.rest.Constantes;
import main.java.fr.ensai.tpjpaensai.domain.Employee;
import main.java.fr.ensai.tpjpaensai.domain.Department;

/**
 * Creation de la base de données
 * @author mathi
 *
 */
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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constantes.connexion);
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
		Department RH = new Department("Relations humaines");
		manager.persist(RH);
		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
		if (numOfEmployees == 0) {
			Employee candidat1 = new Employee("Jakab", "Gipsz", RH);
			candidat1.setDatenaissance(new Date());
			Employee candidat2 = new Employee("Captain", "Nemo", RH);
			candidat2.setDatenaissance(new Date());
			manager.persist(candidat1);
			manager.persist(candidat2);
		}
	}

	private static void listEmployees() {
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employees:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next.getLastName());
		}
	}

	


}