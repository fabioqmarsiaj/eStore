package com.fabioqmarsiaj.estore.dto;

import com.fabioqmarsiaj.estore.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Required")
    @Length(min = 5, max = 80, message = "The size must be between 5 - 80 characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category){
        id = category.getId();
        name = category.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
