package main.java.fr.ensai.tpjpaensai.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reponse {
	
	@Id
	@GeneratedValue
	long id;
	
	@Temporal(TemporalType.DATE)
	Date datereponse;
	
	
	@ManyToOne
	ResponseQuestion reponsequestion;
	
	@ManyToMany
	Collection<ReponsePossible> reponsespossibles;

	public Reponse() {
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

	public ResponseQuestion getReponsequestion() {
		return reponsequestion;
	}

	public void setReponsequestion(ResponseQuestion reponsequestion) {
		this.reponsequestion = reponsequestion;
	}

	public Collection<ReponsePossible> getReponsespossibles() {
		return reponsespossibles;
	}

	public void setReponsespossibles(Collection<ReponsePossible> reponsespossibles) {
		this.reponsespossibles = reponsespossibles;
	}

}
