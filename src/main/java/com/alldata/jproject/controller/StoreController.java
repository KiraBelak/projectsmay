package com.alldata.jproject.controller;

import com.alldata.jproject.entities.Order;
import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.UserRepository;
import com.alldata.jproject.service.impl.OrderServiceImpl;
import com.alldata.jproject.service.impl.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreController {
    @Autowired
    private final ProductsServiceImpl productsService;

    @Autowired
    private final OrderServiceImpl orderService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    StoreController(ProductsServiceImpl productsService, OrderServiceImpl orderService, UserRepository userRepository) {
        this.productsService = productsService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @GetMapping("/store")
    public String showStore(Model model){
        model.addAttribute("products", productsService.getAllProducts());

        return "store";
    }

    @PostMapping("/store/add")
    public String addProduct(@RequestParam("name") String name, @RequestParam("price") String price){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Order order = new Order();

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        Long userId = userRepository.findUserId(email);

        order.setProductName(name);
        order.setPrice(Long.parseLong(price));
        order.setUser(user);
        orderService.storeOrder(userId,order);

        return "redirect:/store";
    }
}
