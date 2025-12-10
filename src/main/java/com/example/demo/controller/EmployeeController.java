package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId") Long id) {
        //return new Employee(id, "atharva", "", 25, true);
        //return employeeRepository.findById(id).orElse(null);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public String getEmployee(@RequestParam(name = "age")  Integer a, @RequestParam(name = "isActive")  Boolean iA) {
        return "Hi employee, your age is " + a + ", your isActive is " + iA;
    }

    @GetMapping(path="/getAllEmployees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee postEmployee(@RequestBody Employee e) {
        return employeeService.saveEmployee(e);
    }

    @PutMapping
    public String checkPut() {
        return "Hello this is response for PUT";
    }

}
