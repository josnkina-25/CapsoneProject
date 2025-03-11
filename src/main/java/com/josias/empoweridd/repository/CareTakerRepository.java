package com.josias.empoweridd.repository;

import com.josias.empoweridd.model.CareTaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareTakerRepository extends JpaRepository<CareTaker, Long> {
    List<CareTaker> findByLastName(String lastName);
}
