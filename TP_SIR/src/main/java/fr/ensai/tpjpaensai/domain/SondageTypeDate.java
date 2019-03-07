package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Entity;

@Entity
public class SondageTypeDate extends Sondage {

	public SondageTypeDate() {
	}
	
	/**
	 * Sondage sur une date
	 * @param titre
	 * @param theme
	 */
	public SondageTypeDate(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
