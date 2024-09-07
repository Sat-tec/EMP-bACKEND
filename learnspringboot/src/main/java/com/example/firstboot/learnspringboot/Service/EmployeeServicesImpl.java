package com.example.firstboot.learnspringboot.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstboot.learnspringboot.EmployeeEntity;
import com.example.firstboot.learnspringboot.Model.Employee;

import com.example.firstboot.learnspringboot.Repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Saved Successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            BeanUtils.copyProperties(employeeEntity, emp);
            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);

            return true;
        }
        return false;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id).get();

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setLastname(employee.getLastname());

        employeeRepository.save(existingEmployee);
        return "Update Successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;

    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        // Split the name into first and last names
    // String[] names = name.split(" ");
    
    // // Check if there are exactly two parts (first and last names)
    // if (names.length != 2) {
    //     // If not exactly two parts, return an empty list
    //     return Collections.emptyList();
    // }
    //     String firstName = names[0];
    // String lastName = names[1];

    List<EmployeeEntity> employeeEntities =
    employeeRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name,
    name);

    
    return employeeEntities.stream().map(entity -> {
    Employee employee = new Employee();
    BeanUtils.copyProperties(entity, employee);
    return employee;
    }).collect(Collectors.toList());
    }

 


    
}
