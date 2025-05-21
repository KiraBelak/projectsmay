package com.alldata.CliProManagementTool.Controllers;

import com.alldata.CliProManagementTool.DTO.PaymentDTO;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Service.ServiceImpl.PaymentServiceImpl;
import lombok.Getter;
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

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createPayment(@RequestBody @Validated PaymentDTO paymentDTO, BindingResult bindingResult){
        try{
            if(bindingResult.hasErrors()){
                List<String> errores = bindingResult.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList());
            }
            Payment createdPayment = paymentService.convertDTOToEntity(paymentDTO);
            paymentService.createPayment(createdPayment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            return ResponseEntity.unprocessableEntity().build();

        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getPaymentsById());
    }

}
