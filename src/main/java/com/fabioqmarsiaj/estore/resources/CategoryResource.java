package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.find(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Category category){
        category =  categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
