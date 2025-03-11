package com.josias.empoweridd.repository;

import com.josias.empoweridd.model.GroupHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupHomeRepository extends JpaRepository<GroupHome, Long> {
    List<GroupHome> findByName(String name);
}
