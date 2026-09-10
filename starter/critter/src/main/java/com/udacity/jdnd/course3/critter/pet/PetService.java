package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class PetService {

    @Autowired 
    PetRepository petRepository;

    public Pet savePet(Pet pet) {
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
    
}
