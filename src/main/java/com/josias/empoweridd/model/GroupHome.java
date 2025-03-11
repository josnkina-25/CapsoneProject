package com.josias.empoweridd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class GroupHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private int capacity;

    @OneToMany(mappedBy = "groupHome")
    private List<Client> clients;

}
