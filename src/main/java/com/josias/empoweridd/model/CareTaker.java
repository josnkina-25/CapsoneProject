package com.josias.empoweridd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString(exclude = "clients")
public class CareTaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;


    private String email;
    private String position;
    private String emergencyContactNumber;
    private String diploma;
    private String gender;

    @OneToMany(mappedBy = "assignedCaretaker")
    private List<Client> clients;
}
