package test.java.fr.ensai.tpjpaensai;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Assert;
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
		Assert.assertEquals(1, getNbEmployeeTest("'LeHanc'"));
	}

	@Test
	public void testModif() {
		modifEmployee();
		Assert.assertEquals(0, getNbEmployeeTest("'LeHanc'"));
		Assert.assertEquals(1, getNbEmployeeTest("'Bleues'"));
	}

	@Test
	public void testSuppression() {
		suppressionEmployee();
		Assert.assertEquals(0, getNbEmployeeTest("'Bleues'"));
	}

	public void ajoutEmployee() {
		init();
		tx.begin();
		Department depTest = new Department("RH");
		manager.persist(depTest);
		Employee employeeTest = new Employee("Mathieu", "LeHanc", depTest);
		manager.persist(employeeTest);
		tx.commit();
	}
	
	public void modifEmployee() {
		init();
		tx.begin();
		TypedQuery<Employee> query;
		query = manager.createQuery("SELECT c FROM Employee c WHERE c.lastName ='LeHanc'", Employee.class);
		Employee employeeTest = query.getSingleResult();
		employeeTest.setLastName("Bleues");
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

	public long getNbEmployeeTest(String lastName) {
		init();
		tx.begin();
		TypedQuery<Long> query;
		query = manager.createQuery("SELECT count(*) FROM Employee WHERE lastName ="+ lastName +"", Long.class);
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
