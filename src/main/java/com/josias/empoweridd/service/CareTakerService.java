package com.josias.empoweridd.service;

import com.josias.empoweridd.model.CareTaker;
import com.josias.empoweridd.repository.CareTakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareTakerService {
    @Autowired
    private CareTakerRepository careTakerRepository;

    public List<CareTaker> getAllCaretakers() {
        return careTakerRepository.findAll();
    }

    public Optional<CareTaker> getCaretakerById(Long id) {
        return careTakerRepository.findById(id);
    }

    public List<CareTaker>getCaretakersByLastName(String lastName) {
        return careTakerRepository.findByLastName(lastName);
    }

   public CareTaker createCareTaker(CareTaker careTaker) {
        careTaker.setId(null);
        return careTakerRepository.save(careTaker);
   }
   public CareTaker updateCareTaker(CareTaker updateCareTaker, Long id) {
        return careTakerRepository.findById(id).map(careTaker -> {
            careTaker.setLastName(updateCareTaker.getLastName());
            careTaker.setFirstName(updateCareTaker.getFirstName());
            careTaker.setContactNumber(updateCareTaker.getContactNumber());
            careTaker.setDiploma(updateCareTaker.getDiploma());
            careTaker.setGender(updateCareTaker.getGender());
            careTaker.setPosition(updateCareTaker.getPosition());
            careTaker.setEmergencyContactNumber(updateCareTaker.getEmergencyContactNumber());
            careTaker.setEmail(updateCareTaker.getEmail());
            return careTakerRepository.save(careTaker);
        }).orElseThrow(() -> new RuntimeException("CareTaker not found"));
   }


    public void deleteCaretaker(Long id) {
        careTakerRepository.deleteById(id);
    }
}
