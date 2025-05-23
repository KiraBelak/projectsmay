package com.alldata.CliProManagementTool.Controllers;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
*/

import com.alldata.CliProManagementTool.DTO.ClientDTO;
import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Service.ServiceImpl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

        private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        try{
            Client savedClient = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(id,clientDTO));
    }



    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
