package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public interface Sondage {

	/*	
	String titre, theme;
	
	@ManyToMany
	@JoinTable(name="Sondage_Choix")
	Collection<Choix> choix;
	
	@OneToMany(mappedBy="q")
	Collection<ReponseChoix> reponses;
*/

	public long getId();

	public void setId(long id);

	public String getTitre();

	public void setTitre(String titre);

	public String getTheme();

	public void setTheme(String theme);

	public Collection<Choix> getChoix();

	public void setChoix(Collection<Choix> choix);

	public Collection<ReponsePossible> getReponses();
}
