package com.fabioqmarsiaj.estore.services;

import com.fabioqmarsiaj.estore.domain.Address;
import com.fabioqmarsiaj.estore.domain.City;
import com.fabioqmarsiaj.estore.domain.Client;
import com.fabioqmarsiaj.estore.domain.ClientType;
import com.fabioqmarsiaj.estore.dto.ClientDTO;
import com.fabioqmarsiaj.estore.dto.NewClientDTO;
import com.fabioqmarsiaj.estore.repositories.AddressRepository;
import com.fabioqmarsiaj.estore.repositories.CityRepository;
import com.fabioqmarsiaj.estore.repositories.ClientRepository;
import com.fabioqmarsiaj.estore.services.exceptions.ClientNotFoundException;
import com.fabioqmarsiaj.estore.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ClientNotFoundException(
                "Client with Id: + " + id + " not found."
        ));
    }

    public Client insert(Client client) {
        client.setId(null);
        client = clientRepository.save(client);
        addressRepository.saveAll(client.getAddresses());
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        Client newClient = find(client.getId());
        updateData(newClient, client);
        return clientRepository.save(newClient);
    }

    public void delete(Integer id) {
        find(id);

        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It's not possible to delete clients associated with orders.");
        }

    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }

    public Client fromDTO(NewClientDTO newClientDTO) {
        Client client = new Client(null, newClientDTO.getName(), newClientDTO.getEmail(), newClientDTO.getCpfOrCnpj(), ClientType.toEnum(newClientDTO.getType()));
        City city = cityRepository.getOne(newClientDTO.getCityId());
        Address address = new Address(null, newClientDTO.getStreet(), newClientDTO.getNumber(), newClientDTO.getComplement(), newClientDTO.getNeighboor(), newClientDTO.getZipCode(), client, city);
        client.getAddresses().add(address);
        client.getPhones().add(newClientDTO.getPhone1());

        if (newClientDTO.getPhone2() != null) {
            client.getPhones().add(newClientDTO.getPhone2());
        }

        if (newClientDTO.getPhone3() != null) {
            client.getPhones().add(newClientDTO.getPhone3());
        }

        return client;
    }

    private void updateData(Client newClient, Client client) {
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }
}
