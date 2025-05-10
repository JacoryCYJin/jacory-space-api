package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.ArticleDTO;
import org.example.jacoryspaceapi.domain.dto.CategoryDTO;
import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.po.ArticlePO;
import org.example.jacoryspaceapi.domain.vo.ArticleVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文章对象转换器
 * @author Jacory
 * @date 2025/5/10
 */
@Component
public class ArticleConverter {

    /**
     * ArticlePO 转 ArticleDTO
     */
    public ArticleDTO converterArticlePOtoArticleDTO(ArticlePO po) {
        if (po == null) {
            return null;
        }

        ArticleDTO dto = new ArticleDTO();
        dto.setNanoid(po.getNanoid());
        dto.setSlug(po.getSlug());
        dto.setTitle(po.getTitle());
        dto.setExcerpt(po.getExcerpt());
        dto.setContent(po.getContent());
        dto.setCoverImage(po.getCoverImage());
        dto.setReadingTime(po.getReadingTime());
        dto.setPublishedDate(po.getPublishedDate());
        dto.setCreateTime(po.getCreateTime());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setCategories(new ArrayList<>());
        dto.setTags(new ArrayList<>());
        
        return dto;
    }
    
    /**
     * ArticlePOList 转 ArticleDTOList
     */
    public List<ArticleDTO> converterArticlePOListToArticleDTOList(List<ArticlePO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }
        
        return poList.stream()
                .map(this::converterArticlePOtoArticleDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * ArticleDTO 转 ArticleVO
     */
    public ArticleVO converterArticleDTOtoArticleVO(ArticleDTO dto) {
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
        vo.setCategories(dto.getCategories());
        vo.setTags(dto.getTags());
        
        return vo;
    }
    
    /**
     * ArticleDTOList 转 ArticleVOList
     */
    public List<ArticleVO> converterArticleDTOListToArticleVOList(List<ArticleDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }
        
        return dtoList.stream()
                .map(this::converterArticleDTOtoArticleVO)
                .collect(Collectors.toList());
    }
    
    /**
     * 填充文章DTO的分类和标签
     * @param articleDTO 文章DTO
     * @param categoryMap 分类Map
     * @param tagMap 标签Map
     * @param articleCategoryMap 文章-分类关系Map
     * @param articleTagMap 文章-标签关系Map
     */
    public void fillArticleDTORelations(
            ArticleDTO articleDTO,
            Map<String, CategoryDTO> categoryMap,
            Map<String, TagDTO> tagMap,
            Map<String, List<String>> articleCategoryMap,
            Map<String, List<String>> articleTagMap) {
        
        String articleNanoid = articleDTO.getNanoid();
        
        // 填充分类
        List<String> categoryNanoids = articleCategoryMap.get(articleNanoid);
        if (categoryNanoids != null && !categoryNanoids.isEmpty()) {
            List<CategoryDTO> categories = categoryNanoids.stream()
                    .map(categoryMap::get)
                    .filter(category -> category != null)
                    .collect(Collectors.toList());
            articleDTO.setCategories(categories);
        }
        
        // 填充标签
        List<String> tagNanoids = articleTagMap.get(articleNanoid);
        if (tagNanoids != null && !tagNanoids.isEmpty()) {
            List<TagDTO> tags = tagNanoids.stream()
                    .map(tagMap::get)
                    .filter(tag -> tag != null)
                    .collect(Collectors.toList());
            articleDTO.setTags(tags);
        }
    }
    
    /**
     * 批量填充文章DTO的分类和标签
     */
    public void fillArticleDTOListRelations(
            List<ArticleDTO> articleDTOList,
            Map<String, CategoryDTO> categoryMap,
            Map<String, TagDTO> tagMap,
            Map<String, List<String>> articleCategoryMap,
            Map<String, List<String>> articleTagMap) {
        
        if (articleDTOList == null || articleDTOList.isEmpty()) {
            return;
        }
        
        for (ArticleDTO articleDTO : articleDTOList) {
            fillArticleDTORelations(articleDTO, categoryMap, tagMap, articleCategoryMap, articleTagMap);
        }
    }
}