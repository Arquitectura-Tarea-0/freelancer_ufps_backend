package com.arqui.ufps.freelancer.controller;

import com.arqui.ufps.freelancer.model.entities.Category;
import com.arqui.ufps.freelancer.repository.dao.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
public class CategoryController {

    @Autowired
    private ICategoryDao categoryDao;

    @GetMapping()
    public List<Category> getCategories(){
        return categoryDao.findAll();
    }

}
