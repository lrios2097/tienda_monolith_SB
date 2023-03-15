package com.monoapp.tienda.service.impl;

import com.monoapp.tienda.Exception.ResourceNotFoundException;
import com.monoapp.tienda.entity.Category;
import com.monoapp.tienda.entity.Product;
import com.monoapp.tienda.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.monoapp.tienda.repository.ProductRepository;
import com.monoapp.tienda.service.IProductService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository; //Inyeccion de dependias de la interfaz Producto repository, esta a su vez tiene JPA para poder heredar aqui
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product guardarProducto(Product product) {
        Long categoryId = product.getCategoryId();
        Category category = categoryRepository.findById(product.getCategoryId()).
                orElseThrow(()->new ResourceNotFoundException("Categoria no existe" + product.getCategoryId()));

        if (categoryId == null) {
            throw new IllegalArgumentException("El campo categoryId es obligatorio");
        }
        product.setCategoryId(product.getCategoryId());
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
    public Product eliminarProducto(Long id) throws NoSuchFieldException {
        Optional<Product> product = productRepository.findById(id); //ubico si existe el product
        if (product.isPresent()){
            Product productEliminado = product.get(); //Capturo el product para mostrar beforedelete
            productRepository.deleteById(id);
            return productEliminado;
        }else{
            throw new NoSuchFieldException("No se encontró el producto con el id especificado");
        }
        /*productRepository.deleteById(id);
        return null;*/
    }

    @Override
    public ResponseEntity<Product> editarProducto(Long id, Product productActualizado) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product productExiste = product.get();
            productExiste.setName(productActualizado.getName());
            productExiste.setPrice(productActualizado.getPrice());
            productExiste.setState(productActualizado.getState());
            productExiste.setCreateAt(productActualizado.getCreateAt());
            Product productoActualizadoBD = productRepository.save(productExiste);
            return ResponseEntity.ok(productoActualizadoBD);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
