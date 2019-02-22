package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

// Une reponse possible est une reponse que l'utilisateur peut choisir. Un Choix aura plusieurs ReponsesPossibles.
@Entity
public class ReponsePossible {
	
	@Id
	@GeneratedValue
	long id;
	
	boolean correct;

	@ManyToOne
	Choix choixConcerne;

	public ReponsePossible() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Choix getQ() {
		return choixConcerne;
	}

	public void setQ(Choix q) {
		this.choixConcerne = q;
	}
}
