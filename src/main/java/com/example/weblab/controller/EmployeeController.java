package com.example.weblab.controller;

import com.example.weblab.model.Employee;
import com.example.weblab.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee updated) {
        return service.updateEmployee(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    // New filtering endpoint: Find employees earning more than the specified salary
    @GetMapping("/filter")
    public List<Employee> filterEmployeesBySalary(@RequestParam Double salaryThreshold) {
        return service.getEmployeesBySalaryThreshold(salaryThreshold);
    }
}
