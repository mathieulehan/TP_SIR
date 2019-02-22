package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Choix {
	
	@Id
	@GeneratedValue
	long id;
	
	@Column(length=2048)
	String enonce;
	boolean multiple;
	
	@ManyToMany(targetEntity=SondageTypeDate.class)
	Collection<Sondage> sondagesDate;

	@ManyToMany(targetEntity=SondageTypeLieu.class)
	Collection<Sondage> sondagesLieu;
	
	@ManyToMany(targetEntity=SondageTypeDateEtLieu.class)
	Collection<Sondage> sondagesDateEtLieu;
	
	@ManyToMany(targetEntity=SondageTypeListeChoix.class)
	Collection<Sondage> sondagesListeChoix;
	
	// les reponses possibles pour ce choix
	@OneToMany(mappedBy="choixConcerne")
	Collection<ReponsePossible> reponsesPossibles;
	
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
//	public Collection<Sondage> getQuestionnaires() {
//		return sondages;
//	}
//	public void setQuestionnaires(Collection<Sondage> questionnaires) {
//		this.sondages = questionnaires;
//	}
	public Collection<ReponsePossible> getReponsepossibles() {
		return reponsesPossibles;
	}
	public void setReponsepossibles(Collection<ReponsePossible> reponsepossibles) {
		this.reponsesPossibles = reponsepossibles;
	}

}
