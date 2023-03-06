package com.monoapp.tienda.controller;

import com.monoapp.tienda.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.monoapp.tienda.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/crear")
    public Product crear(@RequestBody Product product){
        return productService.guardarProducto(product);
    }

    @GetMapping("/listar")
    public List<Product> listar(){
        return productService.listarProduct();
    }

    @GetMapping("buscar/{id}")
    public Product buscar(@PathVariable Long id){
        return productService.buscarproduct(id);
    }

    @GetMapping("/buscar/{name}")
    public List<Product> buscarNombre(String name){
        return productService.listarProduct();
    }


}
