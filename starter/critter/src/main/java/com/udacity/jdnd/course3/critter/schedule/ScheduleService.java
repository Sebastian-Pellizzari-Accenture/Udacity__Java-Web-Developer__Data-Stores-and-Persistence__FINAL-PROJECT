package com.udacity.jdnd.course3.critter.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
@Service 
public class ScheduleService {

    @Autowired 
    ScheduleRepository scheduleRepository;

    @Autowired 
    PetRepository petRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllSchedulesOfAnimal(long petId) {
        return scheduleRepository.findByPetsId(petId);
    }

    public List<Schedule> findAllSchedulesOfEmployee(long employeeId) {
        return scheduleRepository.findByEmployeeId(employeeId);
    }

    public List<Schedule> findAllSchedulesOfCustomer(long customerId) {
        // 1) get all pets of user
        // 2) then get and merge all schedules of each animal 
        List<Pet> petsOfCustomer = petRepository.findByOwnerId(customerId);
        return petsOfCustomer.stream()
            .flatMap(pet -> scheduleRepository.findByPetsId(pet.getId()).stream())
            .toList();
    }    
}
