/**
 * 
 */
package cz.cvut.applyforassignmentportlet.service;

import java.util.List;

/**
 * @author Simeon Kredatus
 *
 */
public interface PersistentDtoService<T> {
	
	void save (T object);
	List<T> getAll();
}
