package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(employee -> modelMapper.map(employee, Employee.class));
    }

    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, Employee.class))
                .collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee e) {
        EmployeeEntity employeeEntity = employeeRepository.save(modelMapper.map(e, EmployeeEntity.class));
        return modelMapper.map(employeeEntity, Employee.class);
    }

    public Employee updateEmployee(Long employeeId, Employee e) {
        EmployeeEntity employeeEntity = modelMapper.map(e, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, Employee.class);
    }

    public boolean deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) {
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public Employee patchEmployee(Long employeeId, Map<String, Object> fieldsToUpdate) {
        boolean exists = employeeRepository.findById(employeeId).isPresent();
        if(!exists)
            return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        fieldsToUpdate.forEach((key, value) -> {
            Field field = ReflectionUtils.getRequiredField(EmployeeEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, employeeEntity, value);
        });
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, Employee.class);
    }
}
