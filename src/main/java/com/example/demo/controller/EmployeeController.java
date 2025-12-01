package com.example.demo.controller;

import com.example.demo.dto.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId") Long id) {
        return new Employee(id, "atharva", "", 25, true);
    }

    @GetMapping
    public String getEmployee(@RequestParam(name = "age")  Integer a, @RequestParam(name = "isActive")  Boolean iA) {
        return "Hi employee, your age is " + a + ", your isActive is " + iA;
    }

    @PostMapping
    public Employee postEmployee(@RequestBody Employee e) {
        e.setAge(25);
        return e;
    }

    @PutMapping
    public String checkPut() {
        return "Hello this is response for PUT";
    }

}
