package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name="employeeId") Long id) {
        //return new Employee(id, "atharva", "", 25, true);
        //return employeeRepository.findById(id).orElse(null);
        Optional<Employee> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public String getEmployee(@RequestParam(name = "age")  Integer a, @RequestParam(name = "isActive")  Boolean iA) {
        return "Hi employee, your age is " + a + ", your isActive is " + iA;
    }

    @GetMapping(path="/getAllEmployees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee e) {
        Employee employee = employeeService.saveEmployee(e);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping(path="{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee e) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, e));
    }

    @DeleteMapping(path="{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployee(employeeId);
        if(gotDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path="{employeeId}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable Long employeeId, @RequestBody Map<String, Object> fieldsToUpdate) {
        Employee employeeDTO = employeeService.patchEmployee(employeeId, fieldsToUpdate);
        if(employeeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }

}
