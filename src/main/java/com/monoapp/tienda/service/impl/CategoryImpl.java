package com.monoapp.tienda.service.impl;

import com.monoapp.tienda.entity.Category;
import com.monoapp.tienda.entity.Product;
import com.monoapp.tienda.repository.CategoryRepository;
import com.monoapp.tienda.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category guardarProduct(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> listarCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category buscarCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> buscarCategoryName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category eliminarCategory(Long id) throws NoSuchFieldException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            Category categoryEliminado = category.get(); // capturo la category para mostrarla before delete
            categoryRepository.deleteById(id);
            return categoryEliminado;
        }else{
            throw new NoSuchFieldException("No se encontró la category con el id especificado");
        }

    }

    @Override
    public ResponseEntity<Category> editarCategory(Long id, Category categoryActualizado) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category categoryexiste = category.get();
            categoryexiste.setName(categoryActualizado.getName());
            categoryexiste.setState(categoryActualizado.getState());
            categoryexiste.setCreateAt(categoryActualizado.getCreateAt());
            Category categoryActualizadoBD = categoryRepository.save(categoryexiste);
            return ResponseEntity.ok(categoryActualizadoBD);

        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
