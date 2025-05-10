package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.CategoryDTO;
import org.example.jacoryspaceapi.domain.po.CategoryPO;
import org.example.jacoryspaceapi.domain.vo.CategoryVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    /**
     * CategoryPO 转 CategoryDTO
     */
    public static CategoryDTO converterCategoryPOtoCategoryDTO(CategoryPO po) {
        if (po == null) {
            return null;
        }

        return new CategoryDTO(po.getNanoid(), po.getName(), po.getDescription());
    }

    /**
     * CategoryPOList 转 CategoryVOList
     */
    public static List<CategoryDTO> converterCategoryPOListToCategoryDTOList(List<CategoryPO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }

        return poList.stream().map(CategoryConverter::converterCategoryPOtoCategoryDTO).collect(Collectors.toList());
    }

    /**
     * CategoryDTO 转 CategoryVO
     */
    public static CategoryVO converterCategoryDTOtoCategoryVO(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return new CategoryVO(dto.getNanoid(), dto.getName(), dto.getDescription());
    }

    /**
     * CategoryDTOList 转 CategoryVOList
     */
    public static List<CategoryVO> converterCategoryDTOListToCategoryVOList(List<CategoryDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }

        return dtoList.stream().map(CategoryConverter::converterCategoryDTOtoCategoryVO).collect(Collectors.toList());
    }

}