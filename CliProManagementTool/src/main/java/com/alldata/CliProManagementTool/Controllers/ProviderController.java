package com.alldata.CliProManagementTool.Controllers;

import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Service.ServiceImpl.ProviderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderServiceImpl providerService;

    public ProviderController(ProviderServiceImpl providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider){
        try{
            Provider createdProvider = providerService.createProvider(provider);
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
