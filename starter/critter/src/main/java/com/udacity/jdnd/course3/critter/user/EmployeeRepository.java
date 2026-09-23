package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

// import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // List<Employee> findByDaysAvailableContains(DayOfWeek day);
    @Query (
        "SELECT e FROM Employee e " +                               // get all employees
        "JOIN e.daysAvailable d " +                                 // make a row for each daysOfTheWeek, so the resulting table is employee x daysOfTheWeek
        "JOIN e.skills s " +                                        // make a row for each skill, so the resulting table is employee x daysOfTheWeek x skills
        "WHERE d = :requiredDay AND s IN :requiredSkills " +        // filter the desired skill and desired dayOfTheWeek
        "GROUP BY e HAVING COUNT(DISTINCT s) = :requiredSkillsSize" // group by employee, considering the nuance that the employee must have ALL skills not just ONE of them
    )
    List<Employee> matchingEmployees(DayOfWeek requiredDay, Set<EmployeeSkill> requiredSkills, long requiredSkillsSize);
}
