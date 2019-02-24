package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReponseUtilisateur {
	
	@Id
	@GeneratedValue
	long id;
	
	@Temporal(TemporalType.DATE)
	Date datereponse;
	
	@OneToMany
	Collection<Employee> utilisateur;
	
	@ManyToOne
	ReponsePossible responsePossible;

	public ReponseUtilisateur() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatereponse() {
		return datereponse;
	}

	public void setDatereponse(Date datereponse) {
		this.datereponse = datereponse;
	}

	public ReponsePossible getReponsequestion() {
		return responsePossible;
	}

	public void setReponsequestion(ReponsePossible reponsePossible) {
		this.responsePossible = reponsePossible;
	}
}
