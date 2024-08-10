package org.example.explore_local.initDB;

import org.example.explore_local.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriesSeeder implements CommandLineRunner {

    private final CategoryService categoryService;


    public CategoriesSeeder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
    }
}
