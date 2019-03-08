package test.java.fr.ensai.tpjpaensai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import fr.istic.sir.rest.Constantes;
import main.java.fr.ensai.tpjpaensai.domain.Department;

public class DepartmentTest {

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
		init();
		tx.begin();
		Department departmentTest = new Department("Test2");
		manager.persist(departmentTest);
		tx.commit();
		assertEquals(Department.class, getDepartmentTest("Test2"));
		assertEquals("Test", getDepartmentTest("Test2").getName());
	}
	
	@Test
	public void testModification() {
		init();
		tx.begin();
		Department departmentTest = getDepartmentTest("'Test2'");
		departmentTest.setName("Test2");
		manager.persist(departmentTest);
		tx.commit();
		assertEquals(Department.class, getDepartmentTest("'Test2'"));
		assertEquals("Test2", getDepartmentTest("'Test2'").getName());
	}
	
	@Test
	public void testSuppresion() {
		init();
		tx.begin();
		Department departmentTest = getDepartmentTest("'Test2'");
		manager.remove(departmentTest);
		tx.commit();
		assertNotEquals(Department.class, getDepartmentTest("'Test2'"));
	}
	
	public Department getDepartmentTest(String name) {
		init();
		tx.begin();
		TypedQuery<Department> query;
		query = manager.createQuery("SELECT c FROM Department c WHERE c.name ="+ name + "", Department.class);
		Department departmentTest = query.getSingleResult();
		tx.commit();
		return departmentTest;
	}
}
