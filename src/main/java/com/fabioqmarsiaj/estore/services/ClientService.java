package com.fabioqmarsiaj.estore.services;

import com.fabioqmarsiaj.estore.domain.Client;
import com.fabioqmarsiaj.estore.repositories.ClientRepository;
import com.fabioqmarsiaj.estore.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ClientNotFoundException(
                "Client with Id: + " + id + " not found."
        ));
    }
}
