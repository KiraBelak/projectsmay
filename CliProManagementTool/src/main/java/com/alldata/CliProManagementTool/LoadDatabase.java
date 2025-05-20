package com.alldata.CliProManagementTool;

import com.alldata.CliProManagementTool.Entities.Client;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Entities.Provider;
import com.alldata.CliProManagementTool.Repository.ClientRepository;
import com.alldata.CliProManagementTool.Repository.PaymentRepository;
import com.alldata.CliProManagementTool.Repository.ProviderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository, ProviderRepository providerRepository, PaymentRepository paymentRepository) {
        return args -> {

            Client newClient = new Client("Rochas cosas","Callejon del novolato #123","ctorres@gmail.com","Carlos Torres");
            Provider newProvider = new Provider("Rochas cosas y mas","burritos","Unos burritos bien buenos");

            log.info("Inserting into Payments "+ paymentRepository.save(new Payment(12000.00,"Descripcion generica",new Client("Rochas cosas","Callejon del novolato #123","ctorres@gmail.com","Carlos Torres"), new Provider("Rochas cosas y mas","burritos","Unos burritos bien buenos"))));

            log.info("Inserting into Clients " + repository.save(new Client("Las tortas del tio carlitos","Callejon del novolato #123","ctorres@gmail.com","Carlos Torres")));
            log.info("Inserting into Clients " + repository.save(new Client("Bethortas vikingas","Periferico de las pacas de pastura #444","bebetortas@gmail.com","Beto Quintero")));

            //? Provider inserts
            log.info("Inserting into Clients " + providerRepository.save(new Provider("Las tortas del tio carlos","quimicos","Unos productos de limpieza")));
            log.info("Inserting into Clients " + providerRepository.save(new Provider("Bethortas vikingas","libros de tortas","Unas vikingas bien buenas")));


        };
    }
}
