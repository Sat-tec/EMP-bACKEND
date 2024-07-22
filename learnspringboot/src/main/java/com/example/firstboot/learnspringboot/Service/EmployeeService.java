package com.example.firstboot.learnspringboot.Service;

import java.util.List;

import com.example.firstboot.learnspringboot.Model.Employee;



public interface EmployeeService {
    String createEmployee(Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
    Employee readEmployee(Long id);
}
