package com.monoapp.tienda.controller;

import com.monoapp.tienda.Exception.ResourceNotFoundException;
import com.monoapp.tienda.entity.Category;
import com.monoapp.tienda.entity.Product;
import com.monoapp.tienda.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monoapp.tienda.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/crear")
    public Product crear(@RequestBody Product product){
        Long categoryId = product.getCategoryId();
        Category category= categoryService.buscarCategory(categoryId);
        if (category == null) {
            throw new ResourceNotFoundException("Categoria no existe " + categoryId);
        }
        product.setCategoryId(category.idCategory);
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

    @GetMapping("/buscar/name/{name}")
    public List<Product> buscarNombre(String name){
        return productService.listarProduct();
    }

    @DeleteMapping("eliminar/{id}")
    public Product eliminarProduct(@PathVariable Long id) throws NoSuchFieldException {
        return productService.eliminarProducto(id);
    }

    @PutMapping("eliminarLog/{id}")
    public Product eliminarProductLog(@PathVariable Long id) throws NoSuchFieldException {
        return productService.eliminarProductoLogica(id);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Product> editarProduct(@PathVariable Long id, @RequestBody Product productActualizado){
        return productService.editarProducto(id, productActualizado);
    }


}
