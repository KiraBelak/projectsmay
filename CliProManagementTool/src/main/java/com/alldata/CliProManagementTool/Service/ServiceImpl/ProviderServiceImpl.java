package com.alldata.CliProManagementTool.Service.ServiceImpl;

import com.alldata.CliProManagementTool.DTO.ProviderDTO;
import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProviderServiceImpl {

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting provider service...");
    }

    @Transactional
    public Provider createProvider(@Valid Provider provider){
            System.out.println("Creating new provider...");
            Provider savedProvider = providerRepository.save(provider);
            System.out.println("Provider created correctly...");
            return savedProvider;
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
        providerDTO.setCompanyName(provider.getCompanyName());

        return providerDTO;
    }

}
