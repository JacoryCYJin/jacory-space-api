package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.WorkPO;
import org.example.jacoryspaceapi.domain.po.WorkTagPO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@Mapper
public interface WorkTagMapper {
    /**
     * 根据作品nanoid查询标签
     *
     * @param workNanoid
     * @return
     */
    List<WorkTagPO> listByWorkNanoids(@Param("workNanoids") List<String> workNanoids);
}
