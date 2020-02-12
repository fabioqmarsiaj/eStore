package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> list(){

        Category informatics = new Category(1, "Informatics");
        Category office = new Category(2, "Office");

        List<Category> categories = new ArrayList<>();
        categories.add(informatics);
        categories.add(office);
        return categories;
    }
}
