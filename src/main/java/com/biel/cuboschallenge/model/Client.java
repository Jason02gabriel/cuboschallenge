package com.biel.cuboschallenge.model;

import com.biel.cuboschallenge.dto.ClientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String FirstName;

    @Column(nullable = false)
    private String LastName;

    @Column(nullable = false)
    private BigDecimal Participation;


    public Client(String firstName, String lastName, BigDecimal participation) {
        FirstName = firstName;
        LastName = lastName;
        Participation = participation;
    }

    public Client(ClientDTO clientDTO) {
        Participation = clientDTO.participation();
        LastName = clientDTO.lastName();
        FirstName = clientDTO.firstName();
    }
}
