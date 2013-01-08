/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.lookforassignment.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cz.cvut.lookforassignment.dto.ProjectDto;
import cz.cvut.lookforassignment.dto.RoleDto;
import cz.cvut.lookforassignment.dto.SkillDto;

/**
 *
 * @author simo
 */
@ManagedBean(name = "searchController")
@ViewScoped
public class SearchController {

    private List<String> selectedSubjects;
    private List<ProjectDto> projects;
    private boolean hasSearched;
    
    public boolean hasSearched() {
        return hasSearched;
    }
    
    public void search() {
        init();
        Collections.shuffle(projects);
        hasSearched = true;
    }
    
    public void sortByRelevance() {
        Collections.shuffle(projects);
    }    
    public void sortByReward() {
        Collections.shuffle(projects);
    }    
    public void sortByWorkload() {
        Collections.shuffle(projects);
    }
    @PostConstruct()
    public void init() {
        projects = new ArrayList<ProjectDto>();
        for (int i = 0; i < 10; i++) {
            ProjectDto project = new ProjectDto();
            project.setName("project" + i);
            project.setDescription("descriptioooon" + i);
            project.setWorkload(150L);
            project.setRoles(initRoles());
            project.setReward(150000L);
            projects.add(project);
        }        
    }

    public boolean isInLimit(int value) {
        if (value <= 3) {
            return true;
        }
        return false;
    }
    public List<RoleDto> initRoles() {
        List<SkillDto> sampleSkills = new ArrayList<SkillDto>();
        List<RoleDto> roles = new ArrayList<RoleDto>();
        for (int i = 0; i < 5; i++) {
            SkillDto skill = new SkillDto();
            skill.setName("Skill name " + i);
            skill.setLevel(i);
            sampleSkills.add(skill);
        }

        roles = new ArrayList<RoleDto>();
        for (int i = 0; i < 10; i++) {
            List<SkillDto> dest = new ArrayList<SkillDto>();
            for (SkillDto skill : sampleSkills) {
                SkillDto s = new SkillDto();
                s.setLevel(skill.getLevel());
                s.setName(skill.getName());
                dest.add(s);
            }
            RoleDto role = new RoleDto();
            role.setId("Role" + i);
            role.setName("Role name " + i);
            role.setNecessarySkills(dest);
            role.setDescription("askldfjklasdfjlkasdjflkjvz\nasdklfjaskldfjklasdfjklasdjfzvmzvnm,nvkasldfjlkasdjf");
            roles.add(role);
        }
        return roles;
    }

    public List<String> departmentOrSubjectComplete(String query) {
        List<String> results = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    public List<String> getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setSelectedSubjects(List<String> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }

    public boolean isHasSearched() {
        return hasSearched;
    }

    public void setHasSearched(boolean hasSearched) {
        this.hasSearched = hasSearched;
    }
    
    
}