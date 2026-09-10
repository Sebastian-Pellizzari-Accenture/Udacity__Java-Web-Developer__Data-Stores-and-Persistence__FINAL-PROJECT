package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name="Schedule")
public class Schedule {
    @Id 
    @GeneratedValue 
    private long id;

    // each employee is part of many schedules
    @ManyToMany 
    private List<Employee> employee;
    // assuming that each pet *may be* a part of multiple schedules
    //      as motivated by the tests
    @ManyToMany
    private List<Pet> pets;
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Employee> getEmployee() {
        return employee;
    }
    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    public List<Pet> getPet() {
        return pets;
    }
    public void setPet(List<Pet> pets) {
        this.pets = pets;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Set<EmployeeSkill> getActivities() {
        return activities;
    }
    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
