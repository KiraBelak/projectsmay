package com.alldata.mobsell.repository;

import com.alldata.mobsell.model.Phone;
import com.alldata.mobsell.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByMake(String make);
    List<Phone> findByUser(User user);
    void deleteByUser(User user);
}
