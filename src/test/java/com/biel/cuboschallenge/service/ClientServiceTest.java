package com.biel.cuboschallenge.service;
import com.biel.cuboschallenge.dto.ClientDTO;
import com.biel.cuboschallenge.exception.NullableException;
import com.biel.cuboschallenge.model.Client;
import com.biel.cuboschallenge.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("John", "Doe", BigDecimal.TEN));
        clients.add(new Client("Jane", "Smith", BigDecimal.valueOf(20)));

        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDTO> clientDTOs = clientService.findAllClients();

        assertEquals(2, clientDTOs.size());
        assertEquals("John", clientDTOs.get(0).firstName());
        assertEquals("Doe", clientDTOs.get(0).lastName());
        assertEquals(BigDecimal.TEN, clientDTOs.get(0).participation());

        assertEquals("Jane", clientDTOs.get(1).firstName());
        assertEquals("Smith", clientDTOs.get(1).lastName());
        assertEquals(BigDecimal.valueOf(20), clientDTOs.get(1).participation());

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testSaveClient() {
        ClientDTO clientDTO = new ClientDTO("John", "Doe", BigDecimal.TEN);
        Client client = new Client("John", "Doe", BigDecimal.TEN);

        when(clientRepository.save(any())).thenReturn(client);

        ClientDTO savedClientDTO = clientService.saveClient(clientDTO);

        assertEquals(clientDTO.firstName(), savedClientDTO.firstName());
        assertEquals(clientDTO.lastName(), savedClientDTO.lastName());
        assertEquals(clientDTO.participation(), savedClientDTO.participation());

        verify(clientRepository, times(1)).save(any());
    }

    @Test
    public void testSaveClient_NullFields() {
        ClientDTO clientDTO = new ClientDTO(null, null, null);

        assertThrows(NullableException.class, () -> clientService.saveClient(clientDTO));

        verify(clientRepository, never()).save(any());
    }

    @Test
    public void testFindByName() {
        Integer clientId = 1;
        Client client = new Client("John", "Doe", BigDecimal.TEN);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        String name = clientService.findByName(clientId);

        assertEquals("John Doe", name);

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testFindByName_ClientNotFound() {
        Integer clientId = 1;

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> clientService.findByName(clientId));

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testFindParticipation() {
        Integer clientId = 1;
        Client client = new Client("John", "Doe", BigDecimal.TEN);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        BigDecimal participation = clientService.findParticipation(clientId);

        assertEquals(BigDecimal.TEN, participation);

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testFindParticipation_ClientNotFound() {
        Integer clientId = 1;

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> clientService.findParticipation(clientId));

        verify(clientRepository, times(1)).findById(clientId);
    }
}



