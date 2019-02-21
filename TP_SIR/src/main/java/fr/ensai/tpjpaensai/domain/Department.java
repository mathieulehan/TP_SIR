package main.java.fr.ensai.tpjpaensai.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {

	private String name;

	public Department(String name) {
		this.name = name;
	}

	@Id
	@Column(length=1024, updatable=true, nullable=false)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
