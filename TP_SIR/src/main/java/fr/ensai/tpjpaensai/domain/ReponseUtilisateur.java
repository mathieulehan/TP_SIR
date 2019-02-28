package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReponseUtilisateur {

	@Id
	@GeneratedValue
	int id;

	@Temporal(TemporalType.DATE)
	Date datereponse;

	@OneToOne
	Employee utilisateur;

	@ManyToOne
	Choix choix;
	
	public ReponseUtilisateur() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatereponse() {
		return datereponse;
	}

	public void setDatereponse(Date datereponse) {
		this.datereponse = datereponse;
	}
}
