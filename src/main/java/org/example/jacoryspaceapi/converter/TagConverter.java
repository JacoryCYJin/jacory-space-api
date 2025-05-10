package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.po.TagPO;
import org.example.jacoryspaceapi.domain.vo.TagVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签对象转换器
 */

public class TagConverter {

    /**
     * TagPO 转 TagDTO
     */
    public static TagDTO converterTagPOtoTagDTO(TagPO po) {
        if (po == null) {
            return null;
        }

        return new TagDTO(po.getNanoid(), po.getName());
    }

    /**
     * TagPO List 转 TagDTO List
     */
    public static List<TagDTO> converterTagPOListToTagDTOList(List<TagPO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }

        return poList.stream().map(TagConverter::converterTagPOtoTagDTO).collect(Collectors.toList());
    }

    /**
     * TagDTO 转 TagVO
     */
    public static TagVO converterTagDTOtoTagVO(TagDTO dto) {
        if (dto == null) {
            return null;
        }

        return new TagVO(dto.getNanoid(), dto.getName());
    }

    /**
     * TagDTO List 转 TagVO List
     */
    public static List<TagVO> converterTagDTOListToTagVOList(List<TagDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }

        return dtoList.stream().map(TagConverter::converterTagDTOtoTagVO).collect(Collectors.toList());
    }

}