package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.jacoryspaceapi.domain.po.WorkPO;

import java.util.List;

/**
*@author Jacory
*@date 2025/5/12
*/
@Mapper
public interface WorkMapper {
    List<WorkPO> list();
}
