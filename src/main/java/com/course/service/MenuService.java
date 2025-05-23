package com.course.service;

import com.course.entity.Menu;
import com.course.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public Menu getMenuById(int id) {
        return menuRepository.findById(id).orElseThrow();
    }
}
