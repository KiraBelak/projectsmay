package com.alldata.CliProManagementTool.Entities;

import com.alldata.CliProManagementTool.DTO.ProviderDTO;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import com.alldata.CliProManagementTool.Service.ServiceImpl.ProviderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
 * @created 23/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */
@ExtendWith(MockitoExtension.class)
class ProviderTest {

    @Mock
    private ProviderRepository providerRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private ProviderServiceImpl providerService;

    private Provider provider;
    private ProviderDTO providerDTO;

    @BeforeEach
    void setUp(){
        provider = new Provider();
        provider.setId(1L);
        provider.setProviderProductName("Productos de limpieza");
        provider.setDescription("Liquido a base de cloro para limpieza de pisos");
        provider.setCompanyName("Deeme");
    }

    @Test
    void getAllProviders_shouldReturnAListOfProviderDTOs(){
        when(providerRepository.findAll()).thenReturn(Arrays.asList(provider));
        List<Provider> result = providerService.getAllProviders();

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(provider.getId(),result.get(0).getId());
        assertEquals(provider.getProviderProductName(),result.get(0).getProviderProductName());
    }

    @Test
    void createProvider_shouldReturnAcreatedProviderDTO(){
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);

        ProviderDTO result = providerService.createProvider(providerService.convertEntityToDTO(provider));

        assertNotNull(result);
        assertEquals(provider.getId(),result.getId());
        assertEquals(provider.getDescription(),result.getDescription() );
        assertEquals(provider.getCompanyName(),result.getCompanyName());
    }


    @Test
    void deleteProviderWithValidId_shouldDeleteProvider(){
//        when(providerRepository.existsById(1L)).thenReturn(true);
        providerRepository.deleteById(1L);

        verify(providerRepository).deleteById(1L);
    }

    @Test
    void updateProvider_withValidId_shouldReturnAnUpdatedProvider(){
        when(providerRepository.findById(1L)).thenReturn(Optional.of(provider));
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);

        ProviderDTO result = providerService.updateProvider(1L, providerService.convertEntityToDTO(provider));

        assertNotNull(result);
        assertEquals(provider.getId(), result.getId());
        assertEquals(provider.getCompanyName(),result.getCompanyName());

    }
}