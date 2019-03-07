package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Entity;

/**
 * Sondage compose d'une liste de choix
 * @author mathi
 *
 */
@Entity
public class SondageTypeListeChoix extends Sondage {
	
	public SondageTypeListeChoix() {
		
	}
	
	public SondageTypeListeChoix(String titre, String theme) {
		this.titre = titre;
		this.theme = theme;
	}
}
