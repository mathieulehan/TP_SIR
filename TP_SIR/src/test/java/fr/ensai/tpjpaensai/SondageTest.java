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
import main.java.fr.ensai.tpjpaensai.domain.Sondage;
import main.java.fr.ensai.tpjpaensai.domain.SondageTypeListeChoix;

public class SondageTest {

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
		Sondage sondageTest = new SondageTypeListeChoix("Sondage", "Test");
		manager.persist(sondageTest);
		assertEquals(SondageTypeListeChoix.class, getSondageTest("'Sondage2'"));
	}
	
	@Test
	public void testModification() {
		init();
		tx.begin();
		Sondage sondageTest = getSondageTest("''Sondage'");
		sondageTest.setTitre("Sondage2");
		manager.persist(sondageTest);
		tx.commit();
		assertEquals(SondageTypeListeChoix.class, getSondageTest("'Sondage2'"));
		assertEquals("Sondage2", getSondageTest("'Sondage2'").getTitre());
	}

	@Test
	public void testSuppresion() {
		init();
		tx.begin();
		Sondage sondageTest = getSondageTest("'Sondage2'");
		manager.remove(sondageTest);
		tx.commit();
		assertNotEquals(SondageTypeListeChoix.class, getSondageTest("'Sondage2'"));
	}
	
	public Sondage getSondageTest(String titre) {
		init();
		tx.begin();
		TypedQuery<Sondage> query;
		query = manager.createQuery("SELECT c FROM Sondage c WHERE c.titre ='Sondage' AND c.theme='Test'", Sondage.class);
		Sondage sondageTest = query.getSingleResult();
		tx.commit();
		return sondageTest;
	}
}
