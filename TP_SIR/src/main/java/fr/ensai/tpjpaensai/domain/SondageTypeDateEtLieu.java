package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Entity;

/**
 * Sondage sur un lieu et une date
 * @author mathi
 *
 */
@Entity
public class SondageTypeDateEtLieu extends Sondage {

	public SondageTypeDateEtLieu() {
		
	}
	
	public SondageTypeDateEtLieu(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
