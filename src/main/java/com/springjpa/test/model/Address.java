package com.springjpa.test.model;

import com.springjpa.test.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Lil
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String StreetName;
    private String houseNumber;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
