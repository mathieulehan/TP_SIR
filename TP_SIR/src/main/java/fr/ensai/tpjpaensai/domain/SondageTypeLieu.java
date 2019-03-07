package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Entity;

/**
 * Sondage sur un lieu
 * @author mathi
 *
 */
@Entity
public class SondageTypeLieu extends Sondage {

	public SondageTypeLieu() {
		
	}
	
	public SondageTypeLieu(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
