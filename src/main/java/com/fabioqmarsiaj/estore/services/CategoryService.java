package com.fabioqmarsiaj.estore.services;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.repositories.CategoryRepository;
import com.fabioqmarsiaj.estore.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new CategoryNotFoundException(
                "Category with Id: + " + id + " not found."
        ));
    }

    public Category insert(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }
}
