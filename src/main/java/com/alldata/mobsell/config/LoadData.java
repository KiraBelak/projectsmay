//package com.alldata.mobsell.config;
//
//import com.alldata.mobsell.model.Phone;
//import com.alldata.mobsell.model.User;
//import com.alldata.mobsell.service.PhoneRepository;
//import com.alldata.mobsell.service.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//public class LoadData {
//    private static final Logger logger = LoggerFactory.getLogger(LoadData.class);
//
//    @Bean
//    @Profile("h2")
//    CommandLineRunner initDatabase(PhoneRepository phoneRepository, UserRepository userRepository) {
//        return args -> {
//            User user = new User("odette@email.com", "Odette");
//            logger.info("Inserting into Users {}", userRepository.save(user));
//            logger.info("Inserting into Phones {}", phoneRepository.save(new Phone("Apple", "iPhone 17", "A15", 32, 22000, userRepository.findUserByEmail("odette@email.com").get())));
//            logger.info("Inserting into Phones {}", phoneRepository.save(new Phone("Samsung", "S23", "Snapdragon 8 Gen 2", 16, 21000, userRepository.findUserByEmail("odette@email.com").get())));
//        };
//    }
//}