package com.josias.empoweridd.serviceTest;

import com.josias.empoweridd.model.CareTaker;
import com.josias.empoweridd.model.Client;
import com.josias.empoweridd.model.GroupHome;
import com.josias.empoweridd.repository.ClientRepository;
import com.josias.empoweridd.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;

    private Client client1;
    private Client client2;
    private GroupHome groupHome;
    private CareTaker careTaker;

    @BeforeEach
   void setUp() {
        groupHome = new GroupHome();
        groupHome.setId(1L);
        groupHome.setName(" Test GroupHome");

        careTaker = new CareTaker();
        careTaker.setId(1L);
        careTaker.setFirstName("John");
        careTaker.setLastName("Doe");
        careTaker.setEmail("john@doe.com");

        client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("John");
        client1.setLastName("Smith");
        client1.setDateOfBirth(LocalDate.of(1999,12,12));
        client1.setGroupHome(groupHome);
        client1.setAssignedCaretaker(careTaker);

        client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Jane");
        client2.setLastName("Doe");
        client2.setDateOfBirth(LocalDate.of(1999,3,30));
        client2.setGroupHome(groupHome);
        client2.setAssignedCaretaker(careTaker);
    }
    @Test
    void getAllClients() {
        //Arrange
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1,client2));

        // Act
        List<Client> clients = clientService.getAllClients();
        //Assert
        assertEquals(2, clients.size());
        assertEquals(clients.contains(client1), true);
        assertEquals(clients.contains(client2), true);
        verify(clientRepository, times(1)).findAll();
    }
    @Test
    void getClientById_ExistingClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client1));
        Optional<Client> foundClient = clientService.getClientById(1L);
        assertEquals(foundClient.isPresent(), true);
        assertEquals(client1, foundClient.get());
        verify(clientRepository,times(1)).findById(1L);
    }
}
