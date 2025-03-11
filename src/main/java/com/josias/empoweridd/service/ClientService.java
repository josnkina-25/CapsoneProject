package com.josias.empoweridd.service;

import com.josias.empoweridd.model.Client;
import com.josias.empoweridd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }


    public List<Client> getClientsByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClientsByGroupHome(Long groupHomeId) {
        return clientRepository.findByGroupHomeId(groupHomeId);
    }

    public List<Client> getClientsByCaretaker(Long caretakerId) {
        return clientRepository.findByAssignedCaretakerId(caretakerId);
    }
   public Client updateClient(Client updatedClient, Long id) {
//        if(id == null) {
//            throw new IllegalArgumentException("Id cannot be null");
//        }
//        return clientRepository.findById(id).map(client -> {
//            client.setFirstName(updatedClient.getFirstName());
//            client.setLastName(updatedClient.getLastName());
//            client.setDateOfBirth(updatedClient.getDateOfBirth());
//            client.setGroupHome(updatedClient.getGroupHome());
//            client.setAssignedCaretaker(updatedClient.getAssignedCaretaker());
//            return clientRepository.save(client);
//        })
//                .orElseThrow(() -> new RuntimeException("client not found"));
       Optional<Client>existingClient = clientRepository.findById(id);
       if (existingClient.isEmpty()) {
           throw new RuntimeException("Client with id " + id + " not found");
       }
       updatedClient.setId(id);
       return clientRepository.save(updatedClient);
   }



    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
