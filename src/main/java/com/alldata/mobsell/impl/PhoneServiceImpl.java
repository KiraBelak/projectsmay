package com.alldata.mobsell.impl;

import com.alldata.mobsell.model.Phone;
import com.alldata.mobsell.model.User;
import com.alldata.mobsell.service.PhoneRepository;
import com.alldata.mobsell.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    public User saveProduct(Long userId, Phone phone) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        phone.setUser(user);
        user.getPhones().add(phone);
        phoneRepository.save(phone);
        return userRepository.save(user);
    }

    public List<Phone> getProducts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        return user.getPhones();
    }

}
