package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired 
    ScheduleService scheduleService;

    @Autowired 
    EmployeeService employeeService;

    @Autowired  
    PetService petService;

    private Schedule dtoToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        // manually set the conversions which spring cannot do for us e.g. pet_id -> Pet
        List<Employee> employees = scheduleDTO.getEmployeeIds()
            .stream()
            .map(employeeService::getById)
            .toList();
        List<Pet> pets = scheduleDTO.getPetIds()
            .stream()
            .map(petService::findById)
            .toList();
        schedule.setEmployee(employees);
        schedule.setPet(pets);
        
        return schedule;
    }

    private ScheduleDTO entityToDto(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        // manually set the conversions which spring cannot do for us e.g. Pet->pet_id
        List<Long> employeeIds = schedule.getEmployee()
            .stream()
            .map(Employee::getId)
            .toList();
        List<Long> petIds = schedule.getPet()
            .stream()
            .map(Pet::getId)
            .toList();
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = dtoToEntity(scheduleDTO);
        schedule = scheduleService.save(schedule);
        return entityToDto(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAll()
            .stream()
            .map(this::entityToDto)
            .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findAllSchedulesOfAnimal(petId)
            .stream()
            .map(this::entityToDto)
            .toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findAllSchedulesOfEmployee(employeeId)
            .stream()
            .map(this::entityToDto)
            .toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findAllSchedulesOfCustomer(customerId)
            .stream()
            .map(this::entityToDto)
            .toList();
    }
}
