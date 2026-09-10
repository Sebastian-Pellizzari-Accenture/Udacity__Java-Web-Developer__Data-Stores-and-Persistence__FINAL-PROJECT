package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import com.udacity.jdnd.course3.critter.pet.Pet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity 
public class Customer extends Person {
    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "note", length = 500)
    private String notes;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Pet> pets;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
