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
    public static TagDTO toTagDTO(TagPO po) {
        if (po == null) {
            return null;
        }
        
        TagDTO dto = new TagDTO();
        dto.setId(po.getId());
        dto.setNanoid(po.getNanoid());
        dto.setName(po.getName());
        dto.setCreatedAt(po.getCreatedAt());
        
        return dto;
    }
    
    /**
     * TagPO 列表转 TagDTO 列表
     */
    public static List<TagDTO> toTagDTOList(List<TagPO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }
        
        return poList.stream()
                .map(TagConverter::toTagDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * TagDTO 转 TagVO
     */
    public static TagVO toTagVO(TagDTO dto) {
        if (dto == null) {
            return null;
        }
        
        TagVO vo = new TagVO();
        vo.setNanoid(dto.getNanoid());
        vo.setName(dto.getName());
        
        return vo;
    }
    
    /**
     * TagDTO 列表转 TagVO 列表
     */
    public static List<TagVO> toTagVOList(List<TagDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        
        return dtoList.stream()
                .map(TagConverter::toTagVO)
                .collect(Collectors.toList());
    }
    
    /**
     * TagPO 转 TagVO
     */
    public static TagVO toTagVO(TagPO po) {
        return toTagVO(toTagDTO(po));
    }
    
    /**
     * TagPO 列表转 TagVO 列表
     */
    public static List<TagVO> toTagVOList(List<TagPO> poList) {
        return toTagVOList(toTagDTOList(poList));
    }
}