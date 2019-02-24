package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.fr.ensai.tpjpaensai.domain.Employee;

@Path("employees")
public class EmployeeService extends AbstractService<Employee>{

	private EntityManager em;

	public EmployeeService() {
		super(Employee.class);
	}	

	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Employee find(@PathParam("id") Integer id) {
		return (super.find(id));
	}

	@GET
	@Produces({ "application/json" })
	public List<Employee> findAll() {
		return super.findAll();
	}

	@GET
	@Path("count")
	@Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}

	protected EntityManager getEntityManager() {
		em = EntitySingleton.getManager();
		return em;
	}
}
