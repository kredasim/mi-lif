/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.lookforassignment.dto;

import java.util.List;

/**
 *
 * @author simo
 */
public class RoleDto {

    private String id;
    private String name;
    private String description;
    private List<SkillDto> necessarySkills;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
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

    public List<SkillDto> getNecessarySkills() {
        return necessarySkills;
    }

    public void setNecessarySkills(List<SkillDto> necessarySkills) {
        this.necessarySkills = necessarySkills;
    }
}