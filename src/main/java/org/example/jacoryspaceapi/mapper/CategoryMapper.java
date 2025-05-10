package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.jacoryspaceapi.domain.po.CategoryPO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface CategoryMapper {
    List<CategoryPO> list();
}
