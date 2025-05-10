package org.example.jacoryspaceapi.controller;

import org.example.jacoryspaceapi.converter.CategoryConverter;
import org.example.jacoryspaceapi.domain.vo.CategoryVO;
import org.example.jacoryspaceapi.common.Result;
import org.example.jacoryspaceapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> categoryVOList = CategoryConverter.converterCategoryDTOListToCategoryVOList(categoryService.list());
        return Result.success(categoryVOList);
    }
}
