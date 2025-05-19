package com.alldata.jproject.service.impl;

import com.alldata.jproject.entities.Product;
import com.alldata.jproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts(){return productsRepository.findAllProducts();}
}
