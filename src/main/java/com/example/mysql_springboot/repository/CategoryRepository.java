package com.example.mysql_springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface de repositório do Spring Data JPA.
import org.springframework.data.jpa.repository.Query; // Importa a anotação de consulta personalizada do Spring Data JPA.
import org.springframework.data.repository.query.Param; // Importa a anotação de parâmetro da consulta personalizada.
import com.example.mysql_springboot.model.Category; // Importa a classe de modelo Category.

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Consulta personalizada usando JPQL (JPA Query Language) para encontrar categorias com um nome específico.
    Category findCategoryByName(String categoryName);

    // Consulta personalizada usando SQL nativo para encontrar categorias com um nome específico.
    @Query(value = "SELECT * FROM category c WHERE c.name = :categoryName", nativeQuery = true)
    Category findCategoryByNameSQL(@Param("categoryName") String categoryName);
}
