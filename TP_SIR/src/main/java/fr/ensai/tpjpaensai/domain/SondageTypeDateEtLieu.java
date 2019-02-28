package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SondageTypeDateEtLieu implements Sondage {

	public SondageTypeDateEtLieu() {
		
	}
	
	public SondageTypeDateEtLieu(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}

	String titre, theme;
	@Id
	@GeneratedValue
	int id;
	
	@ManyToMany
	@JoinTable(name="SondageTypeDateEtLieu_Choix")
	Collection<Choix> choix;
	
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

	public void setChoix(Collection<Choix> choix) {
		this.choix = choix;
	}

	public Collection<Choix> getChoix(){
		return this.choix;
	}
}
