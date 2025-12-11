package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    Long id;
    @NotNull(message = "Name cannot be null")
    String name;
    @Email(message = "Email should be a valid email")
    String email;
    Integer age;
    @JsonProperty("isActive")
    Boolean isActive;
//    @EmployeeRoleValidation // Custom Validation
//    String role;
}
