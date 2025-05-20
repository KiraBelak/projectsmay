package com.alldata.CliProManagementTool.Controllers;

import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Service.ServiceImpl.PaymentServiceImpl;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        try{
            paymentService.createPayment(payment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments(@RequestParam Long id){
        return ResponseEntity.ok(paymentService.getPaymentsById());
    }

}
