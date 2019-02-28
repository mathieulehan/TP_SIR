package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class SondageTypeDate implements Sondage {

	public SondageTypeDate() {
		
	}
	
	public SondageTypeDate(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
	
	String titre, theme;
	
	@Id
	@GeneratedValue
	int id;

	@ManyToMany
	@JoinTable(name="SondageTypeDate_Choix")
	Collection<Choix> choix;

	// a voir si on fait une liste de proposition de dates ou pas
	Date choixUtilisateur;

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

	@OneToOne
	public Collection<Choix> getChoix() {
		return this.choix;
	}

	public void setChoix(Collection<Choix> choix) {
		this.choix = choix;
	}
}
