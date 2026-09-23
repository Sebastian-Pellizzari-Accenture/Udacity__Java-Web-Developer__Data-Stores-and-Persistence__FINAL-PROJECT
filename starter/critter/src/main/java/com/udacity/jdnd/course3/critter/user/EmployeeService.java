package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service 
public class EmployeeService {
    @Autowired 
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return  employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(Long id){
        return employeeRepository.getReferenceById(id);
    }

    @Transactional // multiple database operations
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }
    
    public List<Employee> findEmployeesForService(DayOfWeek requiredDay, Set<EmployeeSkill> requiredSkills) {
        /*
        List<Employee> result = employeeRepository.findByDaysAvailableContains(requiredDay);
        return result.stream()
            .filter(employee -> employee.getSkills()
                .containsAll(requiredSkills))
            .toList();
        */
       return employeeRepository.matchingEmployees(requiredDay, requiredSkills, requiredSkills.size());
    }
}
