package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Un choix proposé a l'utilisateur dans un sondage
 * @author mathi
 *
 */
@Entity
public class Choix {
	
	@Id
	@GeneratedValue
	long id;
	
	@Column(length=2048)
	String enonce;
//	boolean multiple;
	
	// les reponses des utilisateurs
	@OneToMany(mappedBy="choix")
	Collection<ReponseUtilisateur> reponsesUtilisateur;
	
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

	public void addReponseUtilisateur(ReponseUtilisateur reponse) {
		this.reponsesUtilisateur.add(reponse);
	}
	
	public Collection<ReponseUtilisateur> getReponsesUtilisateurs() {
		return this.reponsesUtilisateur;
	}
}
