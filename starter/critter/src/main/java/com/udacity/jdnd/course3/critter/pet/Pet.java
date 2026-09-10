package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.user.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "pet")
public class Pet {
    @Id 
    @GeneratedValue 
    private long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Nationalized 
    private String name;

    @ManyToOne 
    @JoinColumn(name = "owner_id")
    private Customer owner;
    private LocalDate birthDate;

    @Column(name = "note", length = 500)
    private String notes;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public PetType getType() {
        return type;
    }
    public void setType(PetType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Customer getOwner() {
        return owner;
    }
    public void setOwner(Customer owner) {
        this.owner = owner;
    }   
    
}
