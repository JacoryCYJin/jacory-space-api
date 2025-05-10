package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.ArticleCategoryPO;

import java.util.List;

/**
 * 文章-分类关联Mapper接口
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface ArticleCategoryMapper {
    
    /**
     * 根据文章nanoid查询分类nanoid列表
     * @param articleNanoid 文章nanoid
     * @return 分类nanoid列表
     */
    List<String> listCategoryNanoidsByArticleNanoid(@Param("articleNanoid") String articleNanoid);
    
    /**
     * 根据文章nanoid列表查询文章-分类关联
     * @param articleNanoids 文章nanoid列表
     * @return 文章-分类关联列表
     */
    List<ArticleCategoryPO> listByArticleNanoids(@Param("articleNanoids") List<String> articleNanoids);
    
    /**
     * 批量插入文章-分类关联
     * @param list 文章-分类关联列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<ArticleCategoryPO> list);
    
    /**
     * 根据文章nanoid删除文章-分类关联
     * @param articleNanoid 文章nanoid
     * @return 影响行数
     */
    int deleteByArticleNanoid(@Param("articleNanoid") String articleNanoid);
}