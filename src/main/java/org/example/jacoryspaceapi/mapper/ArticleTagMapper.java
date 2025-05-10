package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.ArticleTagPO;

import java.util.List;

/**
 * 文章-标签关联Mapper接口
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface ArticleTagMapper {
    
    /**
     * 根据文章nanoid查询标签nanoid列表
     * @param articleNanoid 文章nanoid
     * @return 标签nanoid列表
     */
    List<String> listTagNanoidsByArticleNanoid(@Param("articleNanoid") String articleNanoid);
    
    /**
     * 根据文章nanoid列表查询文章-标签关联
     * @param articleNanoids 文章nanoid列表
     * @return 文章-标签关联列表
     */
    List<ArticleTagPO> listByArticleNanoids(@Param("articleNanoids") List<String> articleNanoids);
    
    /**
     * 批量插入文章-标签关联
     * @param list 文章-标签关联列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<ArticleTagPO> list);
    
    /**
     * 根据文章nanoid删除文章-标签关联
     * @param articleNanoid 文章nanoid
     * @return 影响行数
     */
    int deleteByArticleNanoid(@Param("articleNanoid") String articleNanoid);
}