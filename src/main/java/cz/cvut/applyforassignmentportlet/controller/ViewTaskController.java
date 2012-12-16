/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.applyforassignmentportlet.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import cz.cvut.fit.bpm.api.dto.AttachmentDto;
import cz.cvut.fit.bpm.api.dto.SkillDto;
import cz.cvut.fit.bpm.api.dto.TaskDto;
import cz.cvut.fit.bpm.api.dto.UnitRoleDto;
import cz.cvut.fit.bpm.api.service.SkillDtoService;
import cz.cvut.fit.bpm.api.service.TaskDtoService;
import cz.cvut.fit.bpm.common.PortletCommonUtils;
import cz.cvut.fit.bpm.common.WorkflowService;
import cz.cvut.fit.industry.api.WorkflowConstants;

/**
 * Controller for view page of this portlet.
 * @author Simeon Kredatus
 *
 */
@ManagedBean
@ViewScoped
public class ViewTaskController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9037053633902916249L;
    
    private static String GREEN_COLOR = "green";
    private static String RED_COLOR = "red";

    @ManagedProperty("#{workflowService}")
    private WorkflowService workflowService;
    
    @ManagedProperty("#{dummySkillDtoService}")
    private SkillDtoService skillDtoService;
    
    @ManagedProperty("#{dummyTaskDtoService}")
    private TaskDtoService taskDtoService;


    /**
     * TODO finish after the method of parameter passing will be known.
     * Method starts new process instance of process called {@link WorkflowConstants.APPLY_FOR_TASK_PROCESS_KEY}.
     * Then the usertask 1 is executed to apply for the offered role.
     * @param roleId
     */
    public void applyFor(int roleId) {
    	String processId = workflowService.startProcess(WorkflowConstants.APPLY_FOR_TASK_PROCESS_KEY);
//    	workflowService.completeTaskByProcessId(null, processId, null);
    }
    
    public String decideColor(String roleName, String skillName) {
        int mySkillLevel = getSkillLevel(skillName);
        int requiredSkillLevel = getRoleSkillLevel(roleName, skillName);
        if (mySkillLevel >= requiredSkillLevel) {
            return GREEN_COLOR;
        }
        return RED_COLOR;
    }
    
    public int getSkillLevel(String skillName) {
    	for (SkillDto skill : skillDtoService.getSkillsForUser(null)) {
    		if (skill.getType().equals(skillName)) {
    			return skill.getRating();
    		}
    	}
    	return 0;
    }
    
    public int getRoleSkillLevel(String roleName, String skillName) {
    	UnitRoleDto unitRoleDto = PortletCommonUtils.getUnitRoleByName(getTask().getRoles(), roleName);
    	for (SkillDto skill : unitRoleDto.getSkillDtoList()) {
    		if (skill.getType().equals(skillName)) {
    			return skill.getRating();
    		}
    	}
    	return 0;
    }

    public void downloadAttachment(AttachmentDto attachment) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("DownloadServlet-1.0-SNAPSHOT/download.do");
    }

    public TaskDto getTask() {
    	return taskDtoService.getById(0L);
    }
    
	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public SkillDtoService getSkillDtoService() {
		return skillDtoService;
	}

	public void setSkillDtoService(SkillDtoService skillDtoService) {
		this.skillDtoService = skillDtoService;
	}

	public TaskDtoService getTaskDtoService() {
		return taskDtoService;
	}

	public void setTaskDtoService(TaskDtoService taskDtoService) {
		this.taskDtoService = taskDtoService;
	}
}
