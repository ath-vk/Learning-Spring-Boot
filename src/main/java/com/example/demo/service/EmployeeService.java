package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(employeeEntity, Employee.class);
    }

    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, Employee.class))
                .collect(Collectors.toList());
    }

//    public boolean saveEmployee(Employee e) {
//        EmployeeEntity employeeEntity = modelMapper.map(e, EmployeeEntity.class);
//    }

}
