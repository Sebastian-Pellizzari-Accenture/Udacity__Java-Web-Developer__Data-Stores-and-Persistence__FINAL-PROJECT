package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * I have chosen to use the InheritanceType.JOINED where common attributes are
* stored in the parent table while subclass-specific attributes are stored in 
* separate tables. 
* The strategy was chosen to avoid redudancy as the tables wont contain any null 
* values, but it sacrifices some performance as the tables have to be joined to 
* get the full picture. However, for this toy-exmple this will not really matter 
* ...
 */
@Entity 
@Table (name = "person")
@Inheritance (strategy = InheritanceType.JOINED)
public class Person {
    @Id 
    @GeneratedValue 
    private Long id;

    @Nationalized 
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
