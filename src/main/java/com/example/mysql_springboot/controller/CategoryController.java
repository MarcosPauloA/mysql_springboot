package com.example.mysql_springboot.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.mysql_springboot.repository.CategoryRepository;
import com.example.mysql_springboot.model.Category;

@RestController // Indica que a classe é um controlador REST
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Mapeia o método para responder a requisições GET na rota base ("/categories")
    @GetMapping
    public List <Category> getAllCategories() {
        return categoryRepository.findAll(); // Retorna todas as categorias no banco de dados
    }

    // Mapeia o método para responder a requisições GET com um parâmetro de caminho (ID).
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null); // Busca a categoria pelo ID.
    }

    // Mapeia o método para responder a requisições GET com um parâmetro de caminho (categoryName).
    @GetMapping("/byName/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName) {
        return categoryRepository.findCategoryByName(categoryName); // Busca a categoria pelo nome usando JPQL.
    }

    // Mapeia o método para responder a requisições GET com um parâmetro de caminho (categoryName).
    @GetMapping("/byNameSql/{categoryName}")
    public Category getCategoryByNameSql(@PathVariable String categoryName) {
        return categoryRepository.findCategoryByNameSQL(categoryName); // Busca a categoria pelo nome usando SQL nativo.
    }

    // Mapeia o método para responder a requisições POST para criar uma nova categoria.
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category); // Cria uma nova categoria no banco de dados.
    }

    // Mapeia o método para responder a requisições PUT com um parâmetro de caminho (ID) para atualizar uma categoria existente.
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElse(null); // Busca a categoria existente pelo ID.
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName()); // Atualiza o nome da categoria.
            return categoryRepository.save(existingCategory); // Salva a categoria atualizada no banco de dados.
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}