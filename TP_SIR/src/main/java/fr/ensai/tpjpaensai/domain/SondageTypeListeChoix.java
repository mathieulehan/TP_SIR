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
	long id;
	String titre, theme;
	
	// Les choix a faire figurer dans ce sondage. Un choix = une liste de reponses pour l'utilisateur
	@ManyToMany
	@JoinTable(name="Sondage_Choix")
	Collection<Choix> choix;

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		// TODO Auto-generated method stub
		
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		// TODO Auto-generated method stub
		
	}

	public Collection<Choix> getChoix() {
		// TODO Auto-generated method stub
		return null;
	}

	// Dans le cas ou on cree un sondage
	public void setChoix(Collection<Choix> choixProposes) {
		this.choix = choixProposes;
	}

	public Collection<ReponsePossible> getReponses() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setReponses(Collection<ReponsePossible> reponses) {
		// TODO Auto-generated method stub
	}
	
}
