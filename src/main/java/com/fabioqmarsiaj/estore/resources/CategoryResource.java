package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.find(id));
    }
}
