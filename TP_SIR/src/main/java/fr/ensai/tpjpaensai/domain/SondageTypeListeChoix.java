package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SondageTypeListeChoix implements Sondage {
	
	public SondageTypeListeChoix() {
		
	}
	
	public SondageTypeListeChoix(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}

	@Id
	@GeneratedValue
	int id;
	String titre, theme;
	
	// Les choix a faire figurer dans ce sondage. Un choix = une liste de reponses pour l'utilisateur
	@ManyToMany
	@JoinTable(name="SondageTypeListe_Choix")
	Collection<Choix> choix;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id  = id;
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

	// Dans le cas ou on cree un sondage
	public void setChoix(Collection<Choix> choixProposes) {
		this.choix = choixProposes;
	}
	
	public Collection<Choix> getChoix(Collection<Choix> choixProposes) {
		return this.choix ;
	}	
}
