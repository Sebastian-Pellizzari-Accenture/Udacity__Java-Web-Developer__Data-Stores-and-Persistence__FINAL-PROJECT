package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity 
public class Employee extends Person {
    // @ManyToMany ~ EmployeeSkill is not an Entity
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    
    // @ManyToMany ~ detto
    @ElementCollection 
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }
    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
