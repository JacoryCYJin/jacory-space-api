package org.example.jacoryspaceapi.service;

import org.example.jacoryspaceapi.domain.dto.CategoryDTO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
public interface CategoryService {
    /**
     * 获取所有分类
     * @return
     */
    List<CategoryDTO> list();
    
    /**
     * 根据nanoid列表查询分类
     * @param nanoids 分类nanoid列表
     * @return 分类列表
     */
    List<CategoryDTO> listByNanoids(List<String> nanoids);
}
