package com.alldata.CliProManagementTool.Controllers;

import com.alldata.CliProManagementTool.DTO.PaymentDTO;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import com.alldata.CliProManagementTool.Service.ServiceImpl.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final ProviderRepository providerRepository;

    public PaymentController(PaymentServiceImpl paymentService,
                             ProviderRepository providerRepository) {
        this.paymentService = paymentService;
        this.providerRepository = providerRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentDTO paymentDTO){
            Payment createdPayment = paymentService.convertDTOToEntity(paymentDTO);
            paymentService.createPayment(createdPayment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getPaymentsById());
    }

}
