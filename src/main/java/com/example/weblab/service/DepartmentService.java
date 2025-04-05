package com.example.weblab.service;

import com.example.weblab.model.Department;
import com.example.weblab.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Add a new department
    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    // Retrieve all departments
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    // Get a department by ID
    public Optional<Department> getDepartmentById(Long id) {
        return repository.findById(id);
    }

    // Update an existing department
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department department = repository.findById(id).orElseThrow();
        department.setName(updatedDepartment.getName());
        return repository.save(department);
    }

    // Delete a department
    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}

@Component
class DepartmentInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;

    public DepartmentInitializer(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() == 0) { // Ensure departments are created only if empty
            departmentRepository.save(new Department("Development"));
            departmentRepository.save(new Department("Human Resources"));
            departmentRepository.save(new Department("Finance"));

            System.out.println("Sample departments added successfully!");
        }
    }
}
