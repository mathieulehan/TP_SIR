package main.java.fr.ensai.tpjpaensai.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Le departement d'un employe
 * @author mathi
 *
 */
@Entity
public class Department {

	private long id;
	
	private String name;

	private List<Employee> employees = 	new ArrayList<Employee>();
	
	public Department() {
		super();
	}
	
	public Department(String name) {
		this.name = name;
	}

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	// Supprimé : (mappedBy = "department", cascade = CascadeType.PERSIST)
	@OneToMany
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
