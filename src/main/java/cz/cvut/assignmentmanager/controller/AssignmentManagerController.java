/**
 * 
 */
package cz.cvut.assignmentmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import cz.cvut.applyforassignmentportlet.service.PersistentDtoService;
import cz.cvut.fit.bpm.api.dto.AttachmentDto;
import cz.cvut.fit.bpm.api.dto.CourseDto;
import cz.cvut.fit.bpm.api.dto.ProjectDto;
import cz.cvut.fit.bpm.api.dto.TaskDto;
import cz.cvut.fit.bpm.api.dto.UnitRoleDto;
import cz.cvut.fit.bpm.common.controller.AbstractDTOController;

/**
 * @author Simeon Kredatus
 *
 */
@ManagedBean(name = "assignmentManager")
@ViewScoped
public class AssignmentManagerController extends AbstractDTOController<TaskDto> {

	@ManagedProperty("#{dummyTaskDtoService}")
	private PersistentDtoService<TaskDto> persistentDtoService;
	
	@PostConstruct
	public void init() {
		//init dto here
		TaskDto dto = new TaskDto();
		dto.setAttachments(new ArrayList<AttachmentDto>());
		dto.setCourse(new ArrayList<CourseDto>());
		dto.setRoles(new ArrayList<UnitRoleDto>());
		dto.setProject(new ProjectDto());
		setDto(dto);
	}
	
	public void save() {
		persistentDtoService.save(getDto());
	}

	public PersistentDtoService<TaskDto> getPersistentDtoService() {
		return persistentDtoService;
	}

	public void setPersistentDtoService(PersistentDtoService<TaskDto> persistentDtoService) {
		this.persistentDtoService = persistentDtoService;
	}
}
