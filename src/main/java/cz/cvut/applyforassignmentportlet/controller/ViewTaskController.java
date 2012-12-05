/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.applyforassignmentportlet.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import cz.cvut.applyforassignmentportlet.dto.AttachmentDto;
import cz.cvut.applyforassignmentportlet.dto.ProcessRoleDto;
import cz.cvut.applyforassignmentportlet.dto.ProjectDto;
import cz.cvut.applyforassignmentportlet.dto.SkillDto;
import cz.cvut.fit.industry.api.WorkflowConstants;
import cz.cvut.fit.industry.api.dto.RoleDto;
import cz.cvut.fit.workflow.common.WorkflowService;

/**
 *
 * @author simo
 */
/**
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
	
	private ProjectDto project;
    private List<ProcessRoleDto> roles;
    private List<SkillDto> mySkills;
    private List<AttachmentDto> attachments;
    
    private static String GREEN_COLOR = "green";
    private static String RED_COLOR = "red";

    @ManagedProperty("#{workflowService}")
    private WorkflowService workflowService;
    /**
     * Creates a new instance of ViewTaskController
     */
    public ViewTaskController() {
    }

    @PostConstruct
    public void init() {
    	project = new ProjectDto();
        project.setName("project1");
        project.setTargets("Some targets");
        project.setDescription("descriptioooon");
        project.setDesiredOutputs("desiredOutputssadfkjzm,nzc,");
        project.setLogonDeadline(new Date());
        project.setNominatedSubject("PA1");
        project.setProject("Parent project -> child project");
        project.setValidFrom(new Date());
        project.setValidTo(new Date());
        project.setWorkloadEstimation(100L);
        project.setSources("some sources");

        initRoles();
        initAttachments();
    }

    //TODO finish after the method of parameter parsing will be known.
    /**
     * Method starts new process instance of process called {@link WorkflowConstants.APPLY_FOR_TASK_PROCESS_KEY}.
     * Then the usertask 1 is executed to apply for the offered role.
     * @param roleId
     */
    public void applyFor(int roleId) {
    	String processId = workflowService.createTask(WorkflowConstants.APPLY_FOR_TASK_PROCESS_KEY);
    	RoleDto role = convertToRoleDto(roles.get(roleId));
    	workflowService.completeTaskByProcessId(processId, null);
    }
    /** 
	 * @param processRoleDto
	 * @return
	 */
	private RoleDto convertToRoleDto(ProcessRoleDto processRoleDto) {
		RoleDto dto = new RoleDto();
		dto.setDescription(processRoleDto.getDescription());
		dto.setName(processRoleDto.getName());
		return dto;
	}

	public void initAttachments() {
        attachments = new ArrayList<AttachmentDto>();

        InputStream stream = ((PortletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/text.txt");
        StreamedContent file = new DefaultStreamedContent(stream, "text/plain", "/text.txt");
        for (int i = 0; i < 5; i++) {
            AttachmentDto a = new AttachmentDto();
            a.setComment("COMMENT " + i);
            a.setFile(file);
            attachments.add(a);

        }
    }

    public void initRoles() {
        List<SkillDto> sampleSkills = new ArrayList<SkillDto>();
        for (int i = 0; i < 5; i++) {
            SkillDto skill = new SkillDto();
            skill.setName("Skill name " + i);
            skill.setLevel(i);
            sampleSkills.add(skill);
        }
        mySkills = sampleSkills;
        
        roles = new ArrayList<ProcessRoleDto>();
        for (int i = 0; i < 10; i++) {
            List<SkillDto> dest = new ArrayList<SkillDto>();
            for (SkillDto skill : mySkills) {
                SkillDto s = new SkillDto();
                s.setLevel(skill.getLevel());
                s.setName(skill.getName());
                dest.add(s);
            }
            ProcessRoleDto role = new ProcessRoleDto();
            role.setId("Role" + i);
            role.setName("Role name " + i);
            role.setNecessarySkills(dest);
            role.setDescription("askldfjklasdfjlkasdjflkjvz\nasdklfjaskldfjklasdfjklasdjfzvmzvnm,nvkasldfjlkasdjf");
            roles.add(role);
        }
        mySkills.get(3).setLevel(1);
    }
    
    public String decideColor(String roleName, String skillName) {
        int mySkillLevel = getSkillLevel(skillName);
        int requiredSkillLevel = getRoleSkillLevel(roleName, skillName);
        if (mySkillLevel >= requiredSkillLevel) {
            return GREEN_COLOR;
        } else {
            return RED_COLOR;
        }
    }
    
    public int getSkillLevel(String skillName) {
        for (SkillDto skill : mySkills) {
            if (skill.getName().equals(skillName)) {
                return skill.getLevel();
            }
        }
        return 0;
    }
    
    public int getRoleSkillLevel(String roleName, String skillName) {
        for (ProcessRoleDto role : roles) {
            for (SkillDto skill : role.getNecessarySkills()) {
                if (role.getName().equals(roleName) && skill.getName().equals(skillName)) {
                    return skill.getLevel();
                }
            }
        }
        return 0;
    }

    public void downloadAttachment(AttachmentDto attachment) throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().redirect("DownloadServlet-1.0-SNAPSHOT/download.do");
    }

    public List<SkillDto> getMySkills() {
        return mySkills;
    }

    public void setMySkills(List<SkillDto> mySkills) {
        this.mySkills = mySkills;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public List<ProcessRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<ProcessRoleDto> roles) {
        this.roles = roles;
    }

    public List<AttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDto> attachments) {
        this.attachments = attachments;
    }

	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
    
}
