package com.alldata.CliProManagementTool.Service.ServiceImpl;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos    
*/

import com.alldata.CliProManagementTool.DTO.ClientDTO;
import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.ClientRepository;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import com.alldata.CliProManagementTool.exceptions.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl {

    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final ProviderRepository providerRepository;


    public ClientServiceImpl(ClientRepository clientRepository,
                             PaymentRepository paymentRepository,
                             ProviderRepository providerRepository) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.providerRepository = providerRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting Client Service...");
    }
    //! Create
    public Client createClient(Client client){
            System.out.println("Creating a new client : "+ client.getName());
            Client savedClient = clientRepository.save(client);
            System.out.println("Creating a new client finished correctly.");
            return savedClient;
    }
    //! Read
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    //! Delete
    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)){
            throw new RuntimeException("Client not found by id, cannot delete.");
        }
        clientRepository.deleteById(id);
    }
    //! Update
    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
        Client currentClient = clientRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Client not found using id: "+ clientDTO.getId()));
        currentClient.setAddress(clientDTO.getAddress());
        currentClient.setEmail(clientDTO.getEmail());
        currentClient.setName(clientDTO.getName());
        currentClient.setRepresentative(clientDTO.getRepresentative());

        if(!paymentRepository.searchPaymentByClientId(clientDTO.getId()).isEmpty()){
            List<Payment> payments = paymentRepository.searchPaymentByClientId(clientDTO.getId());
            currentClient.setPayments(payments.stream().toList());
        }
        Client updatedClient = clientRepository.save(currentClient);

        return convertEntityToDTO(updatedClient);
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

    public Client convertDTOToEntity(ClientDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setRepresentative(clientDTO.getRepresentative());
        if(!clientDTO.getPayments().isEmpty()){
            client.setPayments(clientDTO.getPayments());
        }
        return client;
    }

    public ClientDTO convertEntityToDTO(Client client){
        ClientDTO clientDTO  = new ClientDTO();
        clientDTO.setAddress(client.getAddress());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setRepresentative(client.getRepresentative());
        clientDTO.setId(client.getId());

        if(!client.getPayments().isEmpty()){
            clientDTO.setPayments(client.getPayments());
        }
        return clientDTO;
    }


}
