package com.hungdc.watchstore.services.category;

import com.hungdc.watchstore.dtos.category.CategoryDto;
import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Category;

public interface CategoryService {
    Category getCategory(String id);
    Category create (CategoryDto dto);
    Category update (String id, CategoryDto dto);
    Category delete (String id);
}
