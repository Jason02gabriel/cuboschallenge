package com.biel.cuboschallenge.service;

import com.biel.cuboschallenge.dto.ClientDTO;
import com.biel.cuboschallenge.exception.NullableException;
import com.biel.cuboschallenge.model.Client;
import com.biel.cuboschallenge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clients.forEach(client -> clientDTOS.add(new ClientDTO(client.getFirstName(), client.getLastName(), client.getParticipation())));
        return clientDTOS;
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        if (client.getFirstName() != null || client.getLastName() != null || client.getParticipation() != null) {
            clientRepository.save(client);
        }else {
            throw new NullableException("");
        }
        return new ClientDTO(client.getFirstName(), client.getLastName(), client.getParticipation());
    }

    public String findByName(Integer id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String name = firstName + " " + lastName;
        return name;
    }

    public BigDecimal findParticipation(Integer id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
        return client.getParticipation();
    }


}
