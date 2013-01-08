/**
 * 
 */
package cz.cvut.applyforassignmentportlet.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author Simeon Kredatus
 * 
 */
public class HibernateGenericDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public void save(T object) {
		entityManager.persist(object);

	}

	@SuppressWarnings("unchecked")
	public T findById(long id) {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		return entityManager.find(clazz, id);
	}
	
	public List<T> getAll() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		return entityManager.createQuery("Select a from TaskDto a", clazz).getResultList();
	}

}
