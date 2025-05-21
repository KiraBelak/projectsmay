package com.alldata.jproject.controller;

import com.alldata.jproject.service.impl.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class StoreController {
    private final ProductsServiceImpl productsService;

    @Autowired StoreController(ProductsServiceImpl productsService){
        this.productsService = productsService;
    }

    @GetMapping("/store")
    public String showStore(Model model){
        model.addAttribute("products", productsService.getAllProducts());

        return "store";
    }
}
