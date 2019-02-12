package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Questionnaire {
	
	
	@Id
	@GeneratedValue
	long id;
	
	
	String titre, theme;
	
	public Questionnaire() {
	}
	
	@ManyToMany
	@JoinTable(name="Questionnaire_Question")
	Collection<Question> questions;
	
	@OneToMany(mappedBy="q")
	Collection<ResponseQuestion> reponses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public Collection<ResponseQuestion> getReponses() {
		return reponses;
	}

	public void setReponses(Collection<ResponseQuestion> reponses) {
		this.reponses = reponses;
	}
	

}
