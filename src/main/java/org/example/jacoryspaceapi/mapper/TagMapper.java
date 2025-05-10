package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.TagPO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface TagMapper {

    List<TagPO> list();
    
    /**
     * 根据nanoid列表查询标签
     * @param nanoids 标签nanoid列表
     * @return 标签列表
     */
    List<TagPO> listByNanoids(@Param("nanoids") List<String> nanoids);
}
