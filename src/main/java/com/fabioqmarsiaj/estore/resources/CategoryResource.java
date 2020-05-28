package com.fabioqmarsiaj.estore.resources;

import com.fabioqmarsiaj.estore.domain.Category;
import com.fabioqmarsiaj.estore.dto.CategoryDTO;
import com.fabioqmarsiaj.estore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.find(id));
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<Category> list = categoryService.findAll();

        List<CategoryDTO> listDTO = convertListToDTO(list);
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO){
        Category category= categoryService.fromDTO(categoryDTO);
        category =  categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer id){
        Category category = categoryService.fromDTO(categoryDTO);
        category.setId(id);
        categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerpAGE", defaultValue = "24") Integer linesPerPage,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
        Page<Category> list = categoryService.findPage(page, linesPerPage, direction, orderBy);

        Page<CategoryDTO> listDTO = convertPageToDTO(list);
        return ResponseEntity.ok().body(listDTO);
    }

    private Page<CategoryDTO> convertPageToDTO(Page<Category> list) {
        return list.map(CategoryDTO::new);
    }

    private List<CategoryDTO> convertListToDTO(List<Category> list) {
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
