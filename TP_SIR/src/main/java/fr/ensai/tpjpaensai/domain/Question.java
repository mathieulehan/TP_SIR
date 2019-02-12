package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	long id;
	
	
	@Column(length=2048)
	String enonce;
	boolean multiple;
	@ManyToMany(mappedBy="questions")
	Collection<Questionnaire> questionnaires;
	@OneToMany(mappedBy="q")
	Collection<ReponsePossible> reponsepossibles;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public Collection<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}
	public void setQuestionnaires(Collection<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}
	public Collection<ReponsePossible> getReponsepossibles() {
		return reponsepossibles;
	}
	public void setReponsepossibles(Collection<ReponsePossible> reponsepossibles) {
		this.reponsepossibles = reponsepossibles;
	}

}
