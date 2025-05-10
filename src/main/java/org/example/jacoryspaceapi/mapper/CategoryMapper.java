package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.CategoryPO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface CategoryMapper {
    List<CategoryPO> list();
    
    /**
     * 根据nanoid列表查询分类
     * @param nanoids 分类nanoid列表
     * @return 分类列表
     */
    List<CategoryPO> listByNanoids(@Param("nanoids") List<String> nanoids);
}
