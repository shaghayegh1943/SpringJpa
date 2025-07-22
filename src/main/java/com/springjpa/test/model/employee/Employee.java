package com.springjpa.test.model.employee;

import com.springjpa.test.model.Address;
import com.springjpa.test.model.Department;
import com.springjpa.test.model.Mission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lil
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String identifier;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmpRole role;


    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_mission"
            , joinColumns = @JoinColumn(name = "employee_id")
            , inverseJoinColumns = @JoinColumn(name = "mission_id"))
    private List<Mission> missions;
}
