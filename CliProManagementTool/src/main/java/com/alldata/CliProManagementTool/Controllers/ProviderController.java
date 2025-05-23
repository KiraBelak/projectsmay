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
            providerService.createProvider(providerService.convertEntityToDTO(createdProvider));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id){
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/companyName/{companyName}")
    public ResponseEntity<ProviderDTO> getByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(providerService.findProviderByCompanyName(companyName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(@PathVariable Long id, @Valid @RequestBody ProviderDTO providerDTO){
        return ResponseEntity.ok(providerService.updateProvider(id, providerDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders(){
        return ResponseEntity.ok(providerService.getAllProviders());
    }
}
