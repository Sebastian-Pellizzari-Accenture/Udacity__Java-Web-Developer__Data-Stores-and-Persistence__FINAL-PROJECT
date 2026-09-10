package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<Employee> result = employeeRepository.findByDaysAvailableContains(employeeDTO.getDate().getDayOfWeek());
        return result.stream()
            .filter(employee -> employee.getSkills()
                .containsAll(employeeDTO.getSkills()))
            .toList();
    }
}
