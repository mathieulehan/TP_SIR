package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
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

/**
 * Service gerant les departements
 * @author mathi
 *
 */
@Path("department")
public class DepartmentService extends AbstractService<Department>{

	private EntityManager em;

	public DepartmentService() {
		super(Department.class);
	}	

	@POST
	@Path("create")
	@Produces({"application/json"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String json) throws JSONException {
		JSONObject department = new JSONObject(json);
		Department departmentEntity = new Department();
		departmentEntity.setName(department.getString("name"));
		return super.create(departmentEntity);
	}
	
	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Department find(@PathParam("id") Integer id) {
		return (super.find(id));
	}

	@GET
	@Produces({ "application/json" })
	public List<Department> findAll() {
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
