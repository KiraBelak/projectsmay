package com.alldata.CliProManagementTool.Service.ServiceImpl;

import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostConstruct
    public void start(){
        System.out.println("Starting payment service...");
    }

    public Payment createPayment(Payment payment){
        try{
            System.out.println("Creating new payment...");
            Payment savedPayment = paymentRepository.save(payment);
            System.out.println("Payment created correctly...");
            return savedPayment;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Payment> getPaymentsById(){
        return paymentRepository.findAll();
    }
}
