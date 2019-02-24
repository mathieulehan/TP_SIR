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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Employee {

	String firstName, lastName;
	int id;
	Date datenaissance;
	private Department department;
	
	Collection<ReponseUtilisateur> reponsesChoisies;
	
	public Employee(){}
	
	public Employee(String name, String lastName, Department department) {
		this.firstName = name;
		this.lastName = lastName;
		this.department = department;
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
	
	@Column(length=1024, updatable=false, nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(length=1024, updatable=false, nullable=false)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	@OneToMany(mappedBy="utilisateur")
	public Collection<ReponseUtilisateur> getReponses() {
		return reponsesChoisies;
	}

	public void setReponses(Collection<ReponseUtilisateur> reponsesChoisies) {
		this.reponsesChoisies = reponsesChoisies;
	}
	
    @ManyToOne
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [id=" + this.id + ", firstname=" + this.firstName + ", department="
                + department.getName() + "]";
    }
}
