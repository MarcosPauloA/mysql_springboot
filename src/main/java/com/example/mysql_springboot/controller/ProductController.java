package com.example.mysql_springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import com.example.mysql_springboot.model.Product; // Importa a classe de modelo Product.
import com.example.mysql_springboot.model.Category; // Importa a classe de modelo Category.
import com.example.mysql_springboot.repository.ProductRepository; // Importa o repositório de produtos.
import com.example.mysql_springboot.repository.CategoryRepository; // Importa o repositório de categorias.

@RestController // Indica que esta classe é um controlador REST.
@RequestMapping("/products") // Define o mapeamento base para todas as rotas deste controlador.
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping // Mapeia o método para responder a requisições GET na rota base ("/products").
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Retorna todos os produtos no banco de dados.
    }

    // Mapeia o método para responder a requisições GET com um parâmetro de caminho (ID).
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null); // Busca o produto pelo ID.
    }

    @GetMapping("/category/{categoryId}") // Mapeia o método para responder a requisições GET com um parâmetro de caminho (categoryId).
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null); // Busca a categoria pelo ID.
        if (category != null) {
            return productRepository.findByCategory(category); // Retorna os produtos da categoria.
        }
        return new ArrayList<>(); // Retorna uma lista vazia se a categoria não for encontrada.
    }

    @GetMapping("/below-price/{maxPrice}") // Mapeia o método para responder a requisições GET com um parâmetro de caminho (maxPrice).
    public List<Product> getProductsBelowMaxPrice(@PathVariable double maxPrice) {
        return productRepository.findProductsBelowMaxPrice(maxPrice); // Retorna os produtos com preço abaixo do valor máximo.
    }

    // Mapeia o método para responder a requisições POST para criar um novo produto.
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product); // Cria um novo produto no banco de dados.
    }

    // Mapeia o método para responder a requisições PUT com um parâmetro de caminho (ID) para atualizar um produto existente.
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Category updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null); // Busca a categoria existente pelo ID.
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName()); // Atualiza o nome do produto.
            return productRepository.save(existingProduct); // Salva o produto atualizado no banco de dados.
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
