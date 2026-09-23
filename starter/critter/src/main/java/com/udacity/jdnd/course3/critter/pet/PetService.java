package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;


// Doesn't need transactional, all one liner sql requests
@Service 
public class PetService {

    @Autowired 
    CustomerService customerService;

    @Autowired 
    PetRepository petRepository;

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerService.getById(ownerId);
        pet.setOwner(customer);
        return petRepository.save(pet);
    }

    public Pet findById(long petId) {
        return petRepository.findById(petId)
            .orElseThrow();
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsOfOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public Customer getOwner(long petId) {
        return findById(petId).getOwner();
    }
}
