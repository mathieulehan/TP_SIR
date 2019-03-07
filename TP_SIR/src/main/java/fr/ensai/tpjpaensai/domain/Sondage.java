package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Classe abstraite Sondage
 * @author mathi
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sondage {

	String titre, theme;
	
	@Id
	@GeneratedValue
	int id;
	
	@ManyToMany
	@JoinTable(name="Sondage_Choix")
	Collection<Choix> choix;
	@ManyToOne
	Employee createur;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Collection<Choix> getChoix() {
		return this.choix;
	}

	public void setChoix(Collection<Choix> choix) {
		this.choix = choix;
	}
	
	public void addCreateur(Employee employee) {
		this.createur = employee;
	}
}
