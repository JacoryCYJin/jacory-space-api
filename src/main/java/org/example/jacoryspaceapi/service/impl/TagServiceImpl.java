package org.example.jacoryspaceapi.service.impl;

import org.example.jacoryspaceapi.converter.TagConverter;
import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.vo.TagVO;
import org.example.jacoryspaceapi.mapper.TagMapper;
import org.example.jacoryspaceapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDTO> list() {
        List<TagDTO> tagDTOList = TagConverter.converterTagPOListToTagDTOList(tagMapper.list());
        return tagDTOList;
    }
    
    @Override
    public List<TagDTO> listByNanoids(List<String> nanoids) {
        if (nanoids == null || nanoids.isEmpty()) {
            return List.of();
        }
        List<TagDTO> tagDTOList = TagConverter.converterTagPOListToTagDTOList(tagMapper.listByNanoids(nanoids));
        return tagDTOList;
    }
}
