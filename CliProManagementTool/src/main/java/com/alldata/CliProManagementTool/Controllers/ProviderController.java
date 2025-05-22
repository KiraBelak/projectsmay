package com.alldata.CliProManagementTool.Controllers;

import com.alldata.CliProManagementTool.DTO.ProviderDTO;
import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Service.ServiceImpl.ProviderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/providers")
@Validated
public class ProviderController {

    private final ProviderServiceImpl providerService;

    public ProviderController(ProviderServiceImpl providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProvider(@RequestBody @Valid ProviderDTO provider, BindingResult bindingResult){
        try{
            Provider createdProvider = providerService.convertDtoToEntity(provider);
            providerService.createProvider(createdProvider);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders(){
        return ResponseEntity.ok(providerService.getAllProviders());
    }
}
