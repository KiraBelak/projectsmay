package com.alldata.CliProManagementTool;

import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Repository.ClientRepository;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository, PaymentRepository paymentRepository) {
        return args -> {

//            log.info("Inserting into Clients " + repository.save(new Client("Juan corp","avenida de la campana #34","jrocha@gmail.com","Juan rocha" )));
//            log.info("Inserting into Clients " + repository.save(new Client("Las tortas del tio carlitos","Callejon del novolato #123","ctorres@gmail.com","Carlos Torres")));
//            log.info("Inserting into Clients " + repository.save(new Client("Bethortas vikingas","Periferico de las pacas de pastura #444","bebetortas@gmail.com","Beto Quintero")));
//            paymentRepository.save(new Payment(120.33, ));
//            paymentRepository.save(new Payment(null,999.11,1L,null,null,"Insumos quimicos no tan caros"));
        };
    }
}
