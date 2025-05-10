package org.example.jacoryspaceapi.service.impl;

import org.example.jacoryspaceapi.converter.CategoryConverter;
import org.example.jacoryspaceapi.domain.dto.CategoryDTO;
import org.example.jacoryspaceapi.mapper.CategoryMapper;
import org.example.jacoryspaceapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryDTO> list() {
        List<CategoryDTO> categoryDTOList = CategoryConverter.converterCategoryPOListToCategoryDTOList(categoryMapper.list());
        return categoryDTOList;
    }
    
    @Override
    public List<CategoryDTO> listByNanoids(List<String> nanoids) {
        if (nanoids == null || nanoids.isEmpty()) {
            return List.of();
        }
        List<CategoryDTO> categoryDTOList = CategoryConverter.converterCategoryPOListToCategoryDTOList(categoryMapper.listByNanoids(nanoids));
        return categoryDTOList;
    }
}
