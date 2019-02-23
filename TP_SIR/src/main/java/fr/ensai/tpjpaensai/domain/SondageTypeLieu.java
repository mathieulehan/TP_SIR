package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	public void setChoix(Collection<Choix> choix) {
		// TODO Auto-generated method stub
		
	}
	public Collection<ReponsePossible> getReponses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
