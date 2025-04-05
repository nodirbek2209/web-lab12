package com.example.weblab.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id") // Links Employee to Department table
    private Department department;

    // Constructor
    public Employee(String name, String position, double salary, Department department) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    // Default constructor
    public Employee() {
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
