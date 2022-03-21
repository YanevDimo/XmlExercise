package com.example.xmlexercise.service;

import com.example.xmlexercise.model.dto.CategorySeedDto;
import com.example.xmlexercise.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categories);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
