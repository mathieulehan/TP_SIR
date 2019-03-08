package test.java.fr.ensai.tpjpaensai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import fr.istic.sir.rest.Constantes;
import main.java.fr.ensai.tpjpaensai.domain.Choix;

public class ChoixTest {

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
		Choix ChoixTest = new Choix();
		manager.persist(ChoixTest);
		tx.commit();
		assertEquals(Choix.class, getChoixTest("'Test2'"));
		assertEquals("Test", getChoixTest("'Test2'").getName());
	}
	
	@Test
	public void testModification() {
		init();
		tx.begin();
		Choix ChoixTest = getChoixTest("'Test2'");
		ChoixTest.setName("Test2");
		manager.persist(ChoixTest);
		tx.commit();
		assertEquals(Choix.class, getChoixTest("'Test2'"));
		assertEquals("Test2", getChoixTest("'Test2'").getName());
	}
	
	@Test
	public void testSuppresion() {
		init();
		tx.begin();
		Choix ChoixTest = getChoixTest("'Test2'");
		manager.remove(ChoixTest);
		tx.commit();
		assertNotEquals(Choix.class, getChoixTest("'Test2'"));
	}
	
	public Choix getChoixTest(String name) {
		init();
		tx.begin();
		TypedQuery<Choix> query;
		query = manager.createQuery("SELECT c FROM Choix c WHERE c.name ="+ name + "", Choix.class);
		Choix ChoixTest = query.getSingleResult();
		tx.commit();
		return ChoixTest;
	}
}
