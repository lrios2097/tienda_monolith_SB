package com.monoapp.tienda.service;

import com.monoapp.tienda.entity.Product;

import java.util.List;

public interface IProductService { // Declaro metodos

    public Product guardarProducto (Product product);
    List<Product> listarProduct();
    Product buscarproduct(Long id);

    List <Product> buscarProductName(String name);

    public Product eliminarProducto (Long id);
}
