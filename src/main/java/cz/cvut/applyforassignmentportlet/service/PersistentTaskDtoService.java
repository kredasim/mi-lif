/**
 * 
 */
package cz.cvut.applyforassignmentportlet.service;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cz.cvut.applyforassignmentportlet.dao.TaskDtoGenericDao;
import cz.cvut.fit.bpm.api.dto.TaskDto;
import cz.cvut.fit.bpm.api.service.TaskDtoService;

/**
 * @author Simeon Kredatus
 * 
 */
public class PersistentTaskDtoService implements TaskDtoService,
		PersistentDtoService<TaskDto> {

	private TaskDtoGenericDao taskDtoDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public TaskDto getById(Long id) {
		TaskDto ret = taskDtoDao.findById(id);
		//avoid lazy loading exception
		if (ret.getAttachments() != null) {
			ret.getAttachments().size();
		}
		if (ret.getCourse() != null) {
			ret.getCourse().size();
		}
		if (ret.getRoles() != null) {
			ret.getRoles().size();
		}
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void save(TaskDto object) {
		taskDtoDao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<TaskDto> getAll() {
		return taskDtoDao.getAll();
	}

	/**
	 * @param taskDtoDao
	 *            the taskDtoDao to set
	 */
	@Autowired
	public void setTaskDtoDao(TaskDtoGenericDao taskDtoDao) {
		this.taskDtoDao = taskDtoDao;
	}
}
