package com.monoapp.tienda.controller;

import com.monoapp.tienda.entity.Category;
import com.monoapp.tienda.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService; // inyeccion de dependencias, vamos a utilizar las imp de la interfaz


    @PostMapping("/crear")
    public Category crear(@RequestBody Category category){
        return categoryService.guardarProduct(category);
    }
    @GetMapping("/listar")
    public List<Category> listar(){
        return categoryService.listarCategory();
    }

    @GetMapping("/buscar/{id}")
    public Category buscar(@PathVariable Long id){
        return categoryService.buscarCategory(id);
    }

    @GetMapping("/buscar/name/{name}")
    public List<Category> buscarnombre(String name){
        return categoryService.buscarCategoryName(name);
    }
    @DeleteMapping("/eliminar/{id}")
    public Category eliminarCategory(@PathVariable Long id) throws NoSuchFieldException {
        return categoryService.eliminarCategory(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Category> editarCategory(@PathVariable Long id, @RequestBody Category categoryActualizado){
        return  categoryService.editarCategory(id, categoryActualizado);
    }
}
