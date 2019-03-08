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
		Choix choixTest = new Choix();
		choixTest.setEnonce("Test1");
		manager.persist(choixTest);
		tx.commit();
		assertEquals(Choix.class, getChoixTest("Test1"));
		assertEquals("Test1", getChoixTest("Test1").getEnonce());
		deleteEntity(choixTest);
	}
	
	@Test
	public void testModification() {
		init();
		tx.begin();
		Choix choixTest = new Choix();
		choixTest.setEnonce("Test1");
		choixTest.setEnonce("Test2");
		manager.persist(choixTest);
		tx.commit();
		assertEquals(Choix.class, getChoixTest("'Test2'"));
		assertEquals("Test2", getChoixTest("'Test2'").getEnonce());
	}
	
	@Test
	public void testSuppresion() {
		init();
		tx.begin();
		Choix choixTest = new Choix();
		choixTest.setEnonce("Test2");
		manager.persist(choixTest);
		tx.commit();
		Choix choixTest2 = getChoixTest("'Test2'");
		manager.remove(choixTest2);
		tx.commit();
		assertNotEquals(Choix.class, getChoixTest("'Test2'"));
	}
	
	public Choix getChoixTest(String name) {
		init();
		tx.begin();
		TypedQuery<Choix> query;
		query = manager.createQuery("SELECT c FROM Choix c WHERE c.enonce ="+ name + "", Choix.class);
		Choix choixTest = query.getSingleResult();
		tx.commit();
		return choixTest;
	}
	
	public void deleteEntity(Choix choix) {
		init();
		tx.begin();
		manager.remove(choix);
		tx.commit();
	}
}
