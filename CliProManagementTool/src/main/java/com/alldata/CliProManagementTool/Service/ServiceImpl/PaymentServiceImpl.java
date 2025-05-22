package com.alldata.CliProManagementTool.Service.ServiceImpl;

import com.alldata.CliProManagementTool.DTO.PaymentDTO;
import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Repository.ClientRepository;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import com.alldata.CliProManagementTool.exceptions.NotFoundException;
import com.alldata.CliProManagementTool.exceptions.DoublePaymentException;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ClientRepository clientRepository, ProviderRepository providerRepository) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
        this.providerRepository = providerRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting payment service...");
    }

    public Payment createPayment(@Valid Payment payment){
        try{
            System.out.println("Creating new payment...");
            Payment savedPayment = paymentRepository.save(payment);
            System.out.println("Payment created correctly...");
            return savedPayment;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Payment> getPaymentsById(){
        return paymentRepository.findAll();
    }

    public Payment convertDTOToEntity(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setPaymentDescriptions(paymentDTO.getPaymentDescriptions());
        payment.setQuantity(paymentDTO.getQuantity());
        if(paymentDTO.getClientId() != null && paymentDTO.getProviderId() != null){
            throw new DoublePaymentException("A payment can't be assigned to a client and a provider at the same time");
        }
        if(paymentDTO.getClientId() != null){
            Client client = clientRepository.findById(paymentDTO.getClientId())
                    .orElseThrow(
                            () -> new NotFoundException("Client not found with current id:" + paymentDTO.getClientId()));
//            payment.setClient(client);
            if(paymentDTO.getProviderId() == null){
                System.out.println("No provider id set, correct objetc structure");
            }
        }
        if(paymentDTO.getProviderId() != null){
            Provider provider = providerRepository.findById(paymentDTO.getProviderId())
                    .orElseThrow(
                            () -> new NotFoundException("Provider not found with current id:" + paymentDTO.getClientId()));
                if(paymentDTO.getClientId() == null){
                    System.out.println("No client id set, correct object structure");
                }
        }

        return payment;
    }

    public PaymentDTO convertEntityToDTO(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentDescriptions(payment.getPaymentDescriptions());
        paymentDTO.setQuantity(payment.getQuantity());
        paymentDTO.setId(payment.getId());

        //? Validation to avoid having both a client and a provider attached to a payment
        if(payment.getClient() != null && payment.getProvider() != null){
            throw new DoublePaymentException("CONFLICT! A payment cannot be tied to both a client and a provider at the same time");
        }

        //? Validation for client not found by id
        if(payment.getClient() != null){
            Client client = clientRepository.findById(paymentDTO.getClientId())
                    .orElseThrow(
                            () -> new NotFoundException("Client not found by id: "+ paymentDTO.getClientId()));
        }

        //? Validation for provider not found by id
        if(payment.getProvider() != null){
            Provider provider = providerRepository.findById(paymentDTO.getProviderId())
                    .orElseThrow(
                            () -> new NotFoundException("Provider not found by id"+ paymentDTO.getProviderId()));
        }
        paymentDTO.setClientId(payment.getClient().getId());
        paymentDTO.setProviderId(payment.getProvider().getId());

        return paymentDTO;
    }

}
