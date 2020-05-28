package com.fabioqmarsiaj.estore.services;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.dto.CategoryDTO;
import com.fabioqmarsiaj.estore.repositories.CategoryRepository;
import com.fabioqmarsiaj.estore.services.exceptions.CategoryNotFoundException;
import com.fabioqmarsiaj.estore.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Category update(Category category) {
        Category newCategory = find(category.getId());
        updateData(newCategory, category);
        return categoryRepository.save(newCategory);
    }

    public void delete(Integer id) {
        find(id);

        try{
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It's not possible to delete categories associated with products.");
        }

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return categoryRepository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private void updateData(Category newCategory, Category category) {
        newCategory.setName(category.getName());
    }
}
