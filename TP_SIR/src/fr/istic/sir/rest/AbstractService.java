package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import main.java.fr.ensai.tpjpaensai.domain.Employee;

public abstract class AbstractService<T> {
	private Class<T> entityClass;

	public AbstractService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	/**
	 * Cree une entite
	 * @param entity l'entite qui doit etre cree
	 * @return
	 */
	public Response create(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().persist(entity);
			EntitySingleton.commit();
			if (entity.getClass() == Employee.class){
			return Response.created(null).entity(""+( (Employee) entity).getId())
					.build();
			}
			else {
				return Response.created(null).entity("created")
						.build();
			}
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
		}
	}

	/**
	 * Edite une entite
	 * @param entity l'entite a editer
	 * @return
	 */
	public Response edit(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().merge(entity);
			EntitySingleton.commit();
			return Response.ok().entity("Entity edited successfully").build();
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	/**
	 * Supprime une entite
	 * @param entity l'entite a supprimer
	 * @return
	 */
	public Response remove(T entity) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().remove(getEntityManager().merge(entity));
			EntitySingleton.commit();
			return Response.ok().entity("Entity deleted successfully").build();
		} catch (Exception ex) {
			EntitySingleton.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	/**
	 * Renvoie l'entite demandee
	 * @param id l'identifiant de l'entite
	 * @return
	 */
	public T find(Object id) {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		T obj = getEntityManager().find(entityClass, id);
		EntitySingleton.closeEntityManager();
		return obj;
	}
	
	/**
	 * Renvoie toutes les entites d'un type
	 * @return
	 */
	public List<T> findAll() {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = (List<T>) getEntityManager().createQuery(cq).getResultList();
		EntitySingleton.closeEntityManager();
		return list;
	}

	/**
	 * Renvoie le nombre d'entites d'un certain type
	 * @return
	 */
	public int count() {
		if (!getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().begin();
		}
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
