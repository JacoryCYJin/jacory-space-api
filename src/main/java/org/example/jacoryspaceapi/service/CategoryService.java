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
}
