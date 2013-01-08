/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.lookforassignment.dto;

import java.util.List;

/**
 *
 * @author Simeon Kredatus
 */
public class ProjectDto {
    
    private String name;
    private long workload;
    private String description;
    private List<RoleDto> roles;
    private long reward;
    private String logoUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public long getWorkload() {
        return workload;
    }

    public void setWorkload(long workload) {
        this.workload = workload;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}