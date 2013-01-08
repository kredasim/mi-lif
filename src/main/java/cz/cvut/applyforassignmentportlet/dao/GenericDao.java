/**
 * 
 */
package cz.cvut.applyforassignmentportlet.dao;

import java.util.List;

/**
 * @author Simeon Kredatus
 *
 */
public interface GenericDao<T> {

	void save(T object);
	
	T findById(long id);
	
	List<T> getAll();
}

