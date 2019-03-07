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

import main.java.fr.ensai.tpjpaensai.domain.Department;
import main.java.fr.ensai.tpjpaensai.domain.Employee;

public class EmployeeTest {

	EntityManager manager;
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("localhost");
	private EntityTransaction tx;

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
		factory = Persistence.createEntityManagerFactory("localhost");
		manager = factory.createEntityManager();
		tx = manager.getTransaction();
		tx.begin();
		TypedQuery<Employee> query;
		query = manager.createQuery("SELECT c FROM Employee c WHERE c.lastName ='LeHanc'", Employee.class);
		Employee employeeTest = query.getSingleResult();
		return employeeTest;
	}

	public long getNbEmployeeTest() {
		factory = Persistence.createEntityManagerFactory("localhost");
		manager = factory.createEntityManager();
		tx = manager.getTransaction();
		tx.begin();
		TypedQuery<Long> query;
		query = manager.createQuery("SELECT count(*) FROM Employee WHERE lastName ='LeHanc'", Long.class);
		return query.getSingleResult();
	}

	public void suppressionEmployee() {
		factory = Persistence.createEntityManagerFactory("localhost");
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Employee employeeTest = getEmployeeTest();
		manager.remove(employeeTest);
		manager.getTransaction().commit();
	}
}
