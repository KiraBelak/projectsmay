package com.alldata.CliProManagementTool.Controllers;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
*/

import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Service.ClientService;
import com.alldata.CliProManagementTool.Service.ServiceImpl.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
