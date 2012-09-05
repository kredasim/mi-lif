/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.taskdetail.dto;

import java.util.Date;

/**
 *
 * @author simo
 */
public class ProjectDto {
    
    private String name;
    private String nominatedSubject;
    private String description;
    private String targets;
    private long workloadEstimation;
    private String desiredOutputs;
    private Date validFrom;
    private Date validTo;
    private Date logonDeadline;
    private String project;
    private String sources;
    
    //TODO attachments

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesiredOutputs() {
        return desiredOutputs;
    }

    public void setDesiredOutputs(String desiredOutputs) {
        this.desiredOutputs = desiredOutputs;
    }

    public Date getLogonDeadline() {
        return logonDeadline;
    }

    public void setLogonDeadline(Date logonDeadline) {
        this.logonDeadline = logonDeadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNominatedSubject() {
        return nominatedSubject;
    }

    public void setNominatedSubject(String nominatedSubject) {
        this.nominatedSubject = nominatedSubject;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public long getWorkloadEstimation() {
        return workloadEstimation;
    }

    public void setWorkloadEstimation(long workloadEstimation) {
        this.workloadEstimation = workloadEstimation;
    }
    
    
}
