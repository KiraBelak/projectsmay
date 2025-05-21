package com.alldata.CliProManagementTool.Service.ServiceImpl;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos    
*/

import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Repository.ClientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl {

    private final ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting Client Service...");
    }

    public Client createClient(Client client){
        try{
            System.out.println("Creating a new client : "+ client.getName());
            Client savedClient = clientRepository.save(client);
            System.out.println("Creating a new client finished correctly.");
            return savedClient;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<Client> searchByEmail(String email){
        return clientRepository.searchByClientMail(email);
    }

    public Optional<Client> searchByName(String name){
        return clientRepository.searchByClientName(name);
    }

    public Optional<Client> searchByRepresentative(String representative){
        return clientRepository.searchByClientRepresentative(representative);
    }
}
