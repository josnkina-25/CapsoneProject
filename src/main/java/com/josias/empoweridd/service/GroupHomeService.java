package com.josias.empoweridd.service;

import com.josias.empoweridd.model.GroupHome;
import com.josias.empoweridd.repository.GroupHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupHomeService {
    @Autowired
    private GroupHomeRepository groupHomeRepository;

    public List<GroupHome> getAllGroupHomes() {
        return groupHomeRepository.findAll();
    }

    public Optional<GroupHome> getGroupHomeById(Long id) {
        return groupHomeRepository.findById(id);
    }

    public List<GroupHome> getGroupHomesByName(String name) {
        return groupHomeRepository.findByName(name);
    }

    public GroupHome createGroupHome(GroupHome groupHome) {
        groupHome.setId(null);
        return groupHomeRepository.save(groupHome);
    }
    public GroupHome updateGroupHome(GroupHome updateGroupHome, Long id) {
        return groupHomeRepository.findById(id)
                .map(groupHome -> {
                    groupHome.setName(updateGroupHome.getName());
                    groupHome.setAddress(updateGroupHome.getAddress());
                    groupHome.setCapacity(updateGroupHome.getCapacity());
                    return groupHomeRepository.save(groupHome);
                })
                .orElseThrow(() -> new RuntimeException("Group Home not found with id: " + id));
    }

    public void deleteGroupHome(Long id) {
        groupHomeRepository.deleteById(id);
    }
}
