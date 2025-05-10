package org.example.jacoryspaceapi.service;

import org.example.jacoryspaceapi.domain.dto.TagDTO;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
public interface TagService {

    List<TagDTO> list();
    
    /**
     * 根据nanoid列表查询标签
     * @param nanoids 标签nanoid列表
     * @return 标签列表
     */
    List<TagDTO> listByNanoids(List<String> nanoids);
}
