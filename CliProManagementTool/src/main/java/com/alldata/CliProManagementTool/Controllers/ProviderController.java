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
            if(bindingResult.hasErrors()){
                List<String> errores = bindingResult.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errores);
            }

            Provider provider1 = new Provider();
            provider1.setCompanyName(provider.getCompanyName());
            provider1.setProviderProductName(provider.getProviderProductName());
            provider1.setDescription(provider.getDescription());
            Provider createdProvider = providerService.createProvider(provider1);
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
