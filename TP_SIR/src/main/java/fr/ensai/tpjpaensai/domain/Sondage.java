package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

public interface Sondage {

	/*	
	String titre, theme;
	
	@ManyToMany
	@JoinTable(name="Sondage_Choix")
	Collection<Choix> choix;
	
	@OneToMany(mappedBy="q")
	Collection<ReponseChoix> reponses;
*/

	public int getId();

	public void setId(int id);

	public String getTitre();

	public void setTitre(String titre);

	public String getTheme();

	public void setTheme(String theme);

	public Collection<Choix> getChoix();

	public void setChoix(Collection<Choix> choix);
}
