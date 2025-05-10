package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.CategoryDTO;
import org.example.jacoryspaceapi.domain.po.CategoryPO;
import org.example.jacoryspaceapi.domain.vo.CategoryVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类对象转换器
 */
public class CategoryConverter {

    /**
     * CategoryPO 转 CategoryDTO
     */
    public static CategoryDTO toCategoryDTO(CategoryPO po) {
        if (po == null) {
            return null;
        }
        
        CategoryDTO dto = new CategoryDTO();
        dto.setId(po.getId());
        dto.setNanoid(po.getNanoid());
        dto.setName(po.getName());
        dto.setDescription(po.getDescription());
        dto.setCreatedAt(po.getCreatedAt());
        
        return dto;
    }
    
    /**
     * CategoryPO 列表转 CategoryDTO 列表
     */
    public static List<CategoryDTO> toCategoryDTOList(List<CategoryPO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }
        
        return poList.stream()
                .map(CategoryConverter::toCategoryDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * CategoryDTO 转 CategoryVO
     */
    public static CategoryVO toCategoryVO(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        
        CategoryVO vo = new CategoryVO();
        vo.setNanoid(dto.getNanoid());
        vo.setName(dto.getName());
        vo.setDescription(dto.getDescription());
        
        return vo;
    }
    
    /**
     * CategoryDTO 列表转 CategoryVO 列表
     */
    public static List<CategoryVO> toCategoryVOList(List<CategoryDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        
        return dtoList.stream()
                .map(CategoryConverter::toCategoryVO)
                .collect(Collectors.toList());
    }
    
    /**
     * CategoryPO 转 CategoryVO
     */
    public static CategoryVO toCategoryVO(CategoryPO po) {
        return toCategoryVO(toCategoryDTO(po));
    }
    
    /**
     * CategoryPO 列表转 CategoryVO 列表
     */
    public static List<CategoryVO> toCategoryVOList(List<CategoryPO> poList) {
        return toCategoryVOList(toCategoryDTOList(poList));
    }
}