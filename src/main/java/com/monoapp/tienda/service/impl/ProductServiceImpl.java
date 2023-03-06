package com.monoapp.tienda.service.impl;

import com.monoapp.tienda.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import com.monoapp.tienda.repository.ProductRepository;
import com.monoapp.tienda.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product guardarProducto(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> listarProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product buscarproduct(Long id) {
        return productRepository.findById(id).orElse(null); //Evitar null pointException
        //Otra solicion es utiñizar optional, lo mensiona el IDE
    }

    @Override
    public List<Product> buscarProductName(String name){
        return productRepository.findByName(name);
    }

    @Override
    public Product eliminarProducto(Long id){
        productRepository.deleteById(id);
        return null;
    }
}
