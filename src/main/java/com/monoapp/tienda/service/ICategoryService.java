package com.monoapp.tienda.service;

import com.monoapp.tienda.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ICategoryService {

    public Category guardarProduct (Category category);

    List<Category> listarCategory();

    Category buscarCategory(Long id);

    List<Category> buscarCategoryName(String name);

    public Category eliminarCategory(Long id) throws NoSuchFieldException;

    public ResponseEntity<Category> editarCategory(Long id, Category category);
}
