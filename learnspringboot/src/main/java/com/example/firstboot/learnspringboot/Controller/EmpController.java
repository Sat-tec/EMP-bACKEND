package com.example.firstboot.learnspringboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstboot.learnspringboot.Model.Employee;
import com.example.firstboot.learnspringboot.Service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
// @CrossOrigin("http://localhost:5173/")
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.43.138:5173"})
public class EmpController {
    //List<Employee> employees = new ArrayList<>();
    // EmployeeService employeeService = new EmployeeServicesImpl();

    @Autowired
    private EmployeeService employeeService;

    
   
    // Read Data
    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployees();
    }

    // Search Data by name
    @GetMapping("employees/search")
    public List<Employee> searchEmployeesByName(@RequestParam String name) {
        return employeeService.searchEmployeesByName(name);
    }

    // Read Data By Id
    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.readEmployee(id);
    }

    // Create Data
    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee) {
        //employees.add(employee);
        return employeeService.createEmployee(employee);
    }

    // Delete Data
    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(employeeService.deleteEmployee(id)){
            return "Delete Successfully";
        }else{
            return "Not found";
        }
    }

    // Update Data
    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
    
}
