package com.example.weblab.service;

import com.example.weblab.model.Department;
import com.example.weblab.model.Employee;
import com.example.weblab.repository.DepartmentRepository;
import com.example.weblab.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Retrieve employees by salary threshold
    public List<Employee> getEmployeesBySalaryThreshold(Double salaryThreshold) {
        return repository.findBySalaryGreaterThan(salaryThreshold);
    }

    // Retrieve employees by department
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = repository.findById(id).orElseThrow();
        employee.setName(updatedEmployee.getName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setDepartment(updatedEmployee.getDepartment()); // Update department
        return repository.save(employee);
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}

@Component
class DataInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DataInitializer(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() == 0) {
            Department devDept = departmentRepository.save(new Department("Development"));
            Department hrDept = departmentRepository.save(new Department("Human Resources"));

            employeeRepository.save(new Employee("John Doe", "Developer", 60000, devDept));
            employeeRepository.save(new Employee("Jane Smith", "Manager", 80000, hrDept));
            employeeRepository.save(new Employee("Alice Brown", "Designer", 55000, devDept));

            System.out.println("Sample employees and departments added successfully!");
        }
    }
}
