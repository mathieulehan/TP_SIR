package fr.istic.sir.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import main.java.fr.ensai.tpjpaensai.domain.Department;
import main.java.fr.ensai.tpjpaensai.domain.Employee;
import main.java.fr.ensai.tpjpaensai.domain.Sondage;

/**
 * Service gerant les employees
 * @author mathi
 *
 */
@Path("employees")
public class EmployeeService extends AbstractService<Employee>{

	private EntityManager em;

	public EmployeeService() {
		super(Employee.class);
	}	

	@POST
	@Path("create")
	@Produces({"application/json"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws JSONException, ParseException {
		getEntityManager();
		JSONObject employee = new JSONObject(json);
		Employee employeeEntity = new Employee();
		employeeEntity.setFirstName(employee.getString("firstName"));
		employeeEntity.setLastName(employee.getString("lastName"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.FRENCH);
		Date dateNaissance = formatter.parse((String) employee.get("birthDate"));
		employeeEntity.setDatenaissance((dateNaissance));
		TypedQuery<Department> query;
		Department department = null;
		String departmentName = employee.getString("department");
		query = em.createQuery("SELECT c FROM Department c WHERE c.name = :departmentName" , Department.class).setParameter("departmentName", departmentName);
		department = query.getSingleResult();
		employeeEntity.setDepartment(department);
		return super.create(employeeEntity);
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
