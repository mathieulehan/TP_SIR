package main.java.fr.ensai.tpjpaensai.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * L'employee, qui peut creer des sondages
 * @author mathi
 *
 */
@Entity
public class Employee {

	@Column(length=1024, updatable=true, nullable=false)
	String firstName;
	@Column(length=1024, updatable=true, nullable=false)
	String lastName;
	@Id
	@GeneratedValue
	int id;
	@Temporal(TemporalType.DATE)
	Date datenaissance;
    @ManyToOne
	private Department department;
	@OneToMany(targetEntity=Sondage.class, mappedBy="createur", fetch=FetchType.EAGER)
	Collection<Sondage> sondages;

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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
	public Collection<Sondage> getSondages() {
		return sondages;
	}

	public void setSondages(Collection<Sondage> sondages) {
		this.sondages = sondages;
	}

	public void addSondages(Sondage sondage) {
		this.sondages.add(sondage);
	}
	
    @Override
    public String toString() {
        return "Employee [id=" + this.id + ", firstname=" + this.firstName + ", department="
                + department.getName() + "]";
    }
}
