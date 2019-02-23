package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SondageTypeLieu implements Sondage {

	public SondageTypeLieu() {
		
	}
	
	public SondageTypeLieu(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}

	String titre, theme;
	@Id
	@GeneratedValue
	long id;

	@ManyToMany
	@JoinTable(name="SondageTypeLieu_Choix")
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
		this.titre = titre;
	}
	public String getTheme() {
		return this.theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Collection<Choix> getChoix() {
		return this.getChoix();
	}
	public void setChoix(Collection<Choix> choix) {
		this.choix = choix;
	}
	
	public Collection<ReponsePossible> getReponses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
