package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Client;
import com.fabioqmarsiaj.estore.dto.ClientDTO;
import com.fabioqmarsiaj.estore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(clientService.find(id));
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> listAll(){
        List<Client> list = clientService.findAll();

        List<ClientDTO> listDTO = convertListToDTO(list);
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id){
        Client client = clientService.fromDTO(clientDTO);
        client.setId(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerpAGE", defaultValue = "24") Integer linesPerPage,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
        Page<Client> list = clientService.findPage(page, linesPerPage, direction, orderBy);

        Page<ClientDTO> listDTO = convertPageToDTO(list);
        return ResponseEntity.ok().body(listDTO);
    }

    private Page<ClientDTO> convertPageToDTO(Page<Client> list) {
        return list.map(ClientDTO::new);
    }

    private List<ClientDTO> convertListToDTO(List<Client> list) {
        return list.stream().map(ClientDTO::new).collect(Collectors.toList());
    }
}
