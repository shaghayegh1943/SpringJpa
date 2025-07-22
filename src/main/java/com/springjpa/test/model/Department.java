package com.springjpa.test.model;

import com.springjpa.test.model.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Department {
    @Id
    private Integer departmentId;
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
