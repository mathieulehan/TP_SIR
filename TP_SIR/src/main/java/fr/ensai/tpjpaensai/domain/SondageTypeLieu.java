package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SondageTypeLieu extends Sondage {

	public SondageTypeLieu() {
		
	}
	
	public SondageTypeLieu(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
