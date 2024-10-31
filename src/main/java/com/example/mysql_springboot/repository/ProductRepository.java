package com.example.mysql_springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface de repositório do Spring Data JPA.
import org.springframework.data.jpa.repository.Query; // Importa a anotação de consulta personalizada do Spring Data JPA.
import org.springframework.data.repository.query.Param; // Importa a anotação de parâmetro da consulta personalizada.
import com.example.mysql_springboot.model.Product;
import com.example.mysql_springboot.model.Category;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Método de consulta para encontrar produtos por categoria.
    List<Product> findByCategory(Category category);

    // Consulta personalizada via SQL nativo para encontrar produtos com preço abaixo de um valor máximo.
    @Query(value = "SELECT * FROM product p WHERE p.price < :maxPrice", nativeQuery = true)
    List<Product> findProductsBelowMaxPrice(@Param("maxPrice") double maxPrice);
}
