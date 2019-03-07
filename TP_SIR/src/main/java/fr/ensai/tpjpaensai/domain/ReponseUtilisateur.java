package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Reponse d'un utilisateur a un certain choix
 * @author mathi
 *
 */
@Entity
public class ReponseUtilisateur {

	@Id
	@GeneratedValue
	int id;
//
//	@Temporal(TemporalType.DATE)
//	Date datereponse;

	@ManyToOne
	Choix choix;
	
	String userFirstName, userLastName;
	
	public ReponseUtilisateur() {
	}

	public ReponseUtilisateur(String firstName, String lastName) {
		this.userFirstName = firstName;
		this.userLastName = lastName;
	}
	
	public String getUserFirstName() {
		return this.userFirstName;
	}
	
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	public String getUserLastName() {
		return this.userLastName;
	}
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public Date getDatereponse() {
//		return datereponse;
//	}
//
//	public void setDatereponse(Date datereponse) {
//		this.datereponse = datereponse;
//	}
//	
	public void setChoix(Choix choix) {
		this.choix = choix;
	}
}
