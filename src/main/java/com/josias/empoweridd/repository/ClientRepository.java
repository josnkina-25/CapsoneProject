package com.josias.empoweridd.repository;

import com.josias.empoweridd.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByLastName(String lastName);
    List<Client> findByGroupHomeId(Long groupHomeId);
    List<Client> findByAssignedCaretakerId(Long caretakerId);
}
