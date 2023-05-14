package com.hungdc.watchstore.services.category;

import com.hungdc.watchstore.dtos.category.CategoryDto;
import com.hungdc.watchstore.entities.Category;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.CategoryRepository;
import com.hungdc.watchstore.exceptions.InvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Loại hàng hóa có id %s không tồn tại", id)));
    }

    @Override
    public Category create(CategoryDto dto) {
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên loại hàng không được bỏ trống");
        }
        if (categoryRepository.validateCategory(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Loại hàng có mã %s đã tồn tại",
                    dto.getCode()));
        }
        Category category = new Category();
        category.setCode(dto.getCode().trim().toUpperCase());
        category.setName(dto.getName().trim());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String id, CategoryDto dto) {
        Category category = getCategory(id);
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên loại hàng không được bỏ trống");
        }
        if (!category.getCode().equalsIgnoreCase(dto.getCode().trim())
                && categoryRepository.validateCategory(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Loại hàng có mã %s đã tồn tại",
                    dto.getCode()));
        }
        category.setCode(dto.getCode().trim().toUpperCase());
        category.setName(dto.getName().trim());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category delete(String id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }
}
