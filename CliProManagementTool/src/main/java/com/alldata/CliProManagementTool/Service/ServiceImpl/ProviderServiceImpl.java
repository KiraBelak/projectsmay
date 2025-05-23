package com.alldata.CliProManagementTool.Service.ServiceImpl;

import com.alldata.CliProManagementTool.DTO.ProviderDTO;
import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import com.alldata.CliProManagementTool.exceptions.NotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProviderServiceImpl {

    private final ProviderRepository providerRepository;
    private final PaymentRepository paymentRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository,
                               PaymentRepository paymentRepository) {
        this.providerRepository = providerRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting provider service...");
    }

    @Transactional
    public ProviderDTO createProvider(@Valid ProviderDTO providerDTO){
            System.out.println("Creating new provider...");
            Provider savedProvider = providerRepository.save(convertDtoToEntity(providerDTO));
            System.out.println("Provider created correctly...");
            return convertEntityToDTO(savedProvider);
    }

    public void deleteProvider(Long id){
        if(!providerRepository.existsById(id)){
            throw new NotFoundException("Provider not found with id: "+ id);
        }
    }
    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }

    public Provider convertDtoToEntity(ProviderDTO providerDTO){
        Provider provider = new Provider();
        provider.setProviderProductName(providerDTO.getProviderProductName());
        provider.setDescription(providerDTO.getDescription());
        provider.setCompanyName(providerDTO.getCompanyName());
        return provider;
    }

    public ProviderDTO convertEntityToDTO(Provider provider){
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setProviderProductName(provider.getProviderProductName());
        providerDTO.setDescription(provider.getDescription());
        providerDTO.setId(provider.getId());
        providerDTO.setCompanyName(provider.getCompanyName());
        providerDTO.setPayments(paymentRepository.findById(provider.getId()).stream().toList());
        return providerDTO;
    }

    public ProviderDTO findProviderByCompanyName(String companyName){
        return  providerRepository.searchProviderByCompanyName(companyName);
    }

    public ProviderDTO updateProvider(Long id, ProviderDTO providerDTO) {
        Provider currentProvider = providerRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Could not find provider with id: "+ id));
        currentProvider.setDescription(providerDTO.getDescription());
        currentProvider.setProviderProductName(providerDTO.getProviderProductName());
        currentProvider.setCompanyName(providerDTO.getCompanyName());
        Provider updatedProvider = providerRepository.save(currentProvider);
        return convertEntityToDTO(updatedProvider);
    }
}
