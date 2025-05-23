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
import java.util.stream.Collectors;

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
    //! Create
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
    //! Read
    public List<PaymentDTO> getAllPayments(){
        return paymentRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    //! Read
    public List<Payment> getPaymentsById(){
        return paymentRepository.findAll();
    }

    //! Delete
    public void deletePayment(Long id){
       if(!paymentRepository.existsById(id)){
           throw new NotFoundException("Payment not found by id, can't delete");
       }
       paymentRepository.deleteById(id);
    }

    //! Update
    public PaymentDTO updatePayment(Long id,PaymentDTO paymentDTO){
        Payment currentPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with current id: "+id));

        currentPayment.setPaymentDescriptions(paymentDTO.getPaymentDescriptions());
        currentPayment.setQuantity(paymentDTO.getQuantity());
        //? Update client
        if(paymentDTO.getClientId() != null){
            Client client = clientRepository.findById(paymentDTO.getClientId())
                    .orElseThrow( () -> new NotFoundException("Client not found for id: "+ paymentDTO.getClientId()));
            currentPayment.setClient(client);
        }
        if(paymentDTO.getProviderId() != null){
            Provider provider = providerRepository.findById(paymentDTO.getProviderId())
                    .orElseThrow(() -> new NotFoundException("Provider not found for id: "+ paymentDTO.getProviderId()));
            currentPayment.setProvider(provider);
        }
        Payment updatedPayment = paymentRepository.save(currentPayment);
        return convertEntityToDTO(updatedPayment);
    }

    public List<PaymentDTO> searchPaymentsByDescriptions(String paymentDescription){
        return paymentRepository.searchPaymentByPaymentDescriptions(paymentDescription)
                .stream().map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> searchPaymentsByClientId(Long id){
        return paymentRepository.searchPaymentByClientId(id).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> searchPaymentsByProviderId(Long id){
        return paymentRepository.searchPaymentByProviderId(id).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public Payment convertDTOToEntity(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setPaymentDescriptions(paymentDTO.getPaymentDescriptions());
        payment.setQuantity(paymentDTO.getQuantity());
        //? Validate if the client and the provider are both associated to the payment.
        if(paymentDTO.getClientId() != null && paymentDTO.getProviderId() != null){
            throw new DoublePaymentException("A payment can't be assigned to a client and a provider at the same time");
        }
        if(paymentDTO.getClientId() != null){
            Client client = clientRepository.findById(paymentDTO.getClientId())
                    .orElseThrow(
                            () -> new NotFoundException("Client not found with current id:" + paymentDTO.getClientId()));
            payment.setClient(client);
            //? Check if the provider has been associated. as the client is already been associated.
            if(paymentDTO.getProviderId() == null){
                System.out.println("No provider id set, correct objetc structure");
            }
        }
        if(paymentDTO.getProviderId() != null){
            Provider provider = providerRepository.findById(paymentDTO.getProviderId())
                    .orElseThrow(
                            () -> new NotFoundException("Provider not found with current id:" + paymentDTO.getClientId()));
            //? Check if the client is associated. as the provider is already been associated.
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
            throw new DoublePaymentException("Conflict, A payment cannot be tied to both a client and a provider at the same time");
        }
        //? Validation for client not found by id
        if(payment.getClient() != null){
            paymentDTO.setClientId(payment.getClient().getId());
        }
        //? Validation for provider not found by id
        if(payment.getProvider() != null){
            paymentDTO.setProviderId(payment.getProvider().getId());
        }
        return paymentDTO;
    }

}
