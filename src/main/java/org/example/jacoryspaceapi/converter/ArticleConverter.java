package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.ArticleCreateDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleUpdateDTO;
import org.example.jacoryspaceapi.domain.po.ArticlePO;
import org.example.jacoryspaceapi.domain.vo.ArticleListVO;
import org.example.jacoryspaceapi.domain.vo.ArticleVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章对象转换器
 */
public class ArticleConverter {

    /**
     * ArticleCreateDTO 转 ArticlePO
     */
    public static ArticlePO toArticlePO(ArticleCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ArticlePO po = new ArticlePO();
        po.setTitle(dto.getTitle());
        po.setExcerpt(dto.getExcerpt());
        po.setContent(dto.getContent());
        po.setCoverImage(dto.getCoverImage());
        po.setPublishedDate(dto.getPublishedDate());
        
        // 其他字段如nanoid, slug, readingTime等需要在服务层生成
        
        return po;
    }
    
    /**
     * ArticleUpdateDTO 转 ArticlePO
     */
    public static ArticlePO toArticlePO(ArticleUpdateDTO dto, ArticlePO existingPO) {
        if (dto == null) {
            return existingPO;
        }
        
        if (existingPO == null) {
            existingPO = new ArticlePO();
        }
        
        existingPO.setNanoid(dto.getNanoid());
        existingPO.setTitle(dto.getTitle());
        existingPO.setExcerpt(dto.getExcerpt());
        existingPO.setContent(dto.getContent());
        existingPO.setCoverImage(dto.getCoverImage());
        existingPO.setPublishedDate(dto.getPublishedDate());
        
        return existingPO;
    }
    
    /**
     * ArticlePO 转 ArticleDTO
     */
    public static ArticleDTO toArticleDTO(ArticlePO po) {
        if (po == null) {
            return null;
        }
        
        ArticleDTO dto = new ArticleDTO();
        dto.setId(po.getId());
        dto.setNanoid(po.getNanoid());
        dto.setSlug(po.getSlug());
        dto.setTitle(po.getTitle());
        dto.setExcerpt(po.getExcerpt());
        dto.setContent(po.getContent());
        dto.setCoverImage(po.getCoverImage());
        dto.setReadingTime(po.getReadingTime());
        dto.setPublishedDate(po.getPublishedDate());
        dto.setCreatedAt(po.getCreatedAt());
        dto.setUpdatedAt(po.getUpdatedAt());
        
        return dto;
    }
    
    /**
     * ArticlePO 列表转 ArticleDTO 列表
     */
    public static List<ArticleDTO> toArticleDTOList(List<ArticlePO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }
        
        return poList.stream()
                .map(ArticleConverter::toArticleDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * ArticleDTO 转 ArticleVO
     */
    public static ArticleVO toArticleVO(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ArticleVO vo = new ArticleVO();
        vo.setNanoid(dto.getNanoid());
        vo.setSlug(dto.getSlug());
        vo.setTitle(dto.getTitle());
        vo.setExcerpt(dto.getExcerpt());
        vo.setContent(dto.getContent());
        vo.setCoverImage(dto.getCoverImage());
        vo.setReadingTime(dto.getReadingTime());
        vo.setPublishedDate(dto.getPublishedDate());
        vo.setCreatedAt(dto.getCreatedAt());
        vo.setUpdatedAt(dto.getUpdatedAt());
        
        // 转换关联的分类和标签
        if (dto.getCategories() != null) {
            vo.setCategories(dto.getCategories().stream()
                    .map(CategoryConverter::toCategoryVO)
                    .collect(Collectors.toList()));
        }
        
        if (dto.getTags() != null) {
            vo.setTags(dto.getTags().stream()
                    .map(TagConverter::toTagVO)
                    .collect(Collectors.toList()));
        }
        
        return vo;
    }
    
    /**
     * ArticleDTO 转 ArticleListVO
     */
    public static ArticleListVO toArticleListVO(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ArticleListVO vo = new ArticleListVO();
        vo.setNanoid(dto.getNanoid());
        vo.setSlug(dto.getSlug());
        vo.setTitle(dto.getTitle());
        vo.setExcerpt(dto.getExcerpt());
        vo.setCoverImage(dto.getCoverImage());
        vo.setReadingTime(dto.getReadingTime());
        vo.setPublishedDate(dto.getPublishedDate());
        
        // 转换关联的分类和标签
        if (dto.getCategories() != null) {
            vo.setCategories(dto.getCategories().stream()
                    .map(CategoryConverter::toCategoryVO)
                    .collect(Collectors.toList()));
        }
        
        if (dto.getTags() != null) {
            vo.setTags(dto.getTags().stream()
                    .map(TagConverter::toTagVO)
                    .collect(Collectors.toList()));
        }
        
        return vo;
    }
    
    /**
     * ArticleDTO 列表转 ArticleListVO 列表
     */
    public static List<ArticleListVO> toArticleListVOList(List<ArticleDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        
        return dtoList.stream()
                .map(ArticleConverter::toArticleListVO)
                .collect(Collectors.toList());
    }
    
    /**
     * ArticleDTO 列表转 ArticleVO 列表
     */
    public static List<ArticleVO> toArticleVOList(List<ArticleDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        
        return dtoList.stream()
                .map(ArticleConverter::toArticleVO)
                .collect(Collectors.toList());
    }
}