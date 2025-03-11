package com.josias.empoweridd.controller;

import com.josias.empoweridd.model.Client;
import com.josias.empoweridd.service.CareTakerService;
import com.josias.empoweridd.service.ClientService;
import com.josias.empoweridd.service.GroupHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/clients")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @Autowired
    private GroupHomeService groupHomeService;
    @Autowired
    private CareTakerService careTakerService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "client/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("groupHomes", groupHomeService.getAllGroupHomes());
        model.addAttribute("caretakers", careTakerService.getAllCaretakers());
        return "client/add";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        logger.info("Adding new client: {}", client);
        clientService.createClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Editing client for edit with ID:{}", id);
        Optional<Client> clientOpt = clientService.getClientById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            logger.info("Found client with ID:{}", id);
            model.addAttribute("client", client);
            model.addAttribute("groupHomes", groupHomeService.getAllGroupHomes());
            model.addAttribute("caretakers", careTakerService.getAllCaretakers());
            return "client/edit";
        } else {
            logger.warn("Client with ID:{} not found", id);
            return "redirect:/clients";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable("id") Long id, @ModelAttribute Client client) {
        logger.info("Updating client with ID:{}", id);
        if(client.getId() == null){
            client.setId(id);
            logger.info("Client ID was null, set to: {}", id);
        }

        try {
            Client updatedClient = clientService.updateClient(client,id);
            logger.info("Updated client with ID:{}", updatedClient);
            return "redirect:/clients";

            }catch (Exception e){
            logger.error("Error updating client with ID:{}", id, e);
            return "redirect:/clients";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}

