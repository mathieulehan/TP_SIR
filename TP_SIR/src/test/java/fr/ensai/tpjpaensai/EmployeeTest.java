package test.java.fr.ensai.tpjpaensai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import fr.istic.sir.rest.Constantes;
import main.java.fr.ensai.tpjpaensai.domain.Department;
import main.java.fr.ensai.tpjpaensai.domain.Employee;

public class EmployeeTest {

	EntityManager manager;
	EntityManagerFactory factory = Persistence.createEntityManagerFactory(Constantes.connexion);
	private EntityTransaction tx;

	public void init() {
		factory = Persistence.createEntityManagerFactory(Constantes.connexion);
		manager = factory.createEntityManager();
		tx = manager.getTransaction();
	}
	
	@Test
	public void testAjout() {
		ajoutEmployee();
		assertEquals(1, getNbEmployeeTest());
	}

	@Test
	public void testSuppression() {
		suppressionEmployee();
		assertEquals(0, getNbEmployeeTest());
	}

	public void ajoutEmployee() {
		manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Department depTest = new Department("RH");
		manager.persist(depTest);
		Employee employeeTest = new Employee("Mathieu", "LeHanc", depTest);
		manager.persist(employeeTest);
		tx.commit();
	}

	public Employee getEmployeeTest() {
		init();
		tx.begin();
		TypedQuery<Employee> query;
		query = manager.createQuery("SELECT c FROM Employee c WHERE c.lastName ='LeHanc'", Employee.class);
		Employee employeeTest = query.getSingleResult();
		tx.commit();
		return employeeTest;
	}

	public long getNbEmployeeTest() {
		init();
		tx.begin();
		TypedQuery<Long> query;
		query = manager.createQuery("SELECT count(*) FROM Employee WHERE lastName ='LeHanc'", Long.class);
		tx.commit();
		return query.getSingleResult();
	}

	public void suppressionEmployee() {
		init();
		tx.begin();
		Employee employeeTest = getEmployeeTest();
		manager.remove(employeeTest);
		tx.commit();
	}
}
