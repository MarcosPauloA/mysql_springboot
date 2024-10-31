package com.example.mysql_springboot.model;

import jakarta.persistence.Entity; // Importa a anotação de entidade do Jakarta Persistence.
import jakarta.persistence.GeneratedValue; // Importa a anotação de geração automática.
import jakarta.persistence.GenerationType; // Importa a estratégia de geração automática.
import jakarta.persistence.Id; // Importa a anotação de chave primária.
import jakarta.persistence.Column; // Importa a anotação de coluna.
import jakarta.persistence.ManyToOne; // Importa a anotação de relacionamento Many-to-One.
import jakarta.persistence.JoinColumn; // Importa a anotação de coluna de junção.

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária do produto.

    @Column(nullable = false)
    private String name; // Nome do produto, não pode ser nulo.

    @Column(nullable = false)
    private double price; // Preço do produto, não pode ser nulo.

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Categoria à qual o produto pertence.

    // Getters e setters para as propriedades da entidade Product.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
