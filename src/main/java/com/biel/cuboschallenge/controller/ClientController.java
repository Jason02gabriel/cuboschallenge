package com.biel.cuboschallenge.controller;


import com.biel.cuboschallenge.dto.ClientDTO;
import com.biel.cuboschallenge.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> findAllClients() {
        return clientService.findAllClients();
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @GetMapping("/{id}")
    public String findByName(@PathVariable Integer id){
        return clientService.findByName(id);
    }

    @GetMapping("participation/{id}")
    public BigDecimal findParticipation(@PathVariable Integer id){
        return clientService.findParticipation(id);
    }


}
