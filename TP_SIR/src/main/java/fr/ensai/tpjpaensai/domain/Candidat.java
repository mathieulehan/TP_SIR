package main.java.fr.ensai.tpjpaensai.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Candidat {

	String name;
	long numeroEtudiant;
	Date datenaissance;

	Collection<ResponseQuestion> reponses;
	
	public Candidat(String name, Department department) {
	}
	
	private  int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
	
	@Transient
	public int getAge() {
		
		LocalDate date1 = Instant.ofEpochMilli(
				datenaissance.getTime()).atZone(
						ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = Instant.ofEpochMilli(
				System.currentTimeMillis()).
				atZone(ZoneId.systemDefault()).toLocalDate();
		return calculateAge( date1,date2
				);
		
	}
	
	@Column(length=1024, updatable=false,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public long getNumeroEtudiant() {
		return numeroEtudiant;
	}

	public void setNumeroEtudiant(long numeroEtudiant) {
		this.numeroEtudiant = numeroEtudiant;
	}

	@Temporal(TemporalType.DATE)
	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	@OneToMany(mappedBy="c")
	public Collection<ResponseQuestion> getReponses() {
		return reponses;
	}

	public void setReponses(Collection<ResponseQuestion> reponses) {
		this.reponses = reponses;
	}

}
