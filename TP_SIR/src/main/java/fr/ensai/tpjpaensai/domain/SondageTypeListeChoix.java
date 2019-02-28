package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SondageTypeListeChoix extends Sondage {
	
	public SondageTypeListeChoix() {
		
	}
	
	public SondageTypeListeChoix(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
