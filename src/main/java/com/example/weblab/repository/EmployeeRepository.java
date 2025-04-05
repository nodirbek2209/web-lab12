package com.example.weblab.repository;

import com.example.weblab.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalaryGreaterThan(Double salary);

    // Fix: Add method to find employees by department ID
    List<Employee> findByDepartmentId(Long departmentId);
}
