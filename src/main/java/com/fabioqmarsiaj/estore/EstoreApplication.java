package com.fabioqmarsiaj.estore;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.domain.Product;
import com.fabioqmarsiaj.estore.repositories.CategoryRepository;
import com.fabioqmarsiaj.estore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EstoreApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(EstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informatics");
        Category cat2 = new Category(null, "Office");

        Product prod1 = new Product(null, "Computer", 2000.0);
        Product prod2 = new Product(null, "Scanner", 800.0);
        Product prod3 = new Product(null, "Mouse", 80.0);

        cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));

        cat2.getProducts().addAll(Arrays.asList(prod2));

        prod1.getCategories().addAll(Arrays.asList(cat1));

        prod2.getCategories().addAll(Arrays.asList(cat1, cat2));

        prod3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
    }
}
