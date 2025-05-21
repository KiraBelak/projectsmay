package com.alldata.mobsell.service;

import com.alldata.mobsell.model.Phone;
import com.alldata.mobsell.model.User;
import com.alldata.mobsell.repository.PhoneRepository;
import com.alldata.mobsell.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository,UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public List<Phone> findByMake(String make) {
        return phoneRepository.findByMake(make);
    }

    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    public List<Phone> findByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return phoneRepository.findByUser(user.get()).stream().toList();
        }
        throw new RuntimeException("User with id: " + id + " not found");
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(phoneRepository::deleteByUser);
    }
}