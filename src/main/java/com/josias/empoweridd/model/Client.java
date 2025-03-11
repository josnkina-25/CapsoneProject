package com.josias.empoweridd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@ToString(exclude={"assignedCaretaker", "groupHome"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")

    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @Size(max = 1000, message = "Medical history cannot exceed 1000 characters")
    private String medicalHistory;

    @ManyToOne
    @JoinColumn(name = "caretaker_id")
    private CareTaker assignedCaretaker;

    @ManyToOne
    @JoinColumn(name = "group_home_id")
    private GroupHome groupHome;

}
