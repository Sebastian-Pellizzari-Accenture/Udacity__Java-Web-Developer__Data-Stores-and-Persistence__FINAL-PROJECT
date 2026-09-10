package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired 
    PetService petService;

    @Autowired 
    CustomerService customerService;

    private Pet dtoToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = customerService.getById(petDTO.getOwnerId());
        pet.setOwner(customer);
        return pet;
    }

    private PetDTO entityToDto(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        Customer customer = pet.getOwner();
        petDTO.setOwnerId(customer.getId());
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = dtoToEntity(petDTO);
        pet = petService.savePet(pet);
        return entityToDto(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findById(petId);
        return entityToDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets()
            .stream()
            .map(this::entityToDto)
            .toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsOfOwner(ownerId)
            .stream()
            .map(this::entityToDto)
            .toList();
    }
}
