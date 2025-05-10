package org.example.jacoryspaceapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jacoryspaceapi.domain.po.ArticlePO;

import java.util.Date;
import java.util.List;

/**
 * 文章Mapper接口
 * @author Jacory
 * @date 2025/5/10
 */
@Mapper
public interface ArticleMapper {
    
    /**
     * 查询所有文章
     * @return 文章列表
     */
    List<ArticlePO> list();
    
    /**
     * 根据nanoid查询文章
     * @param nanoid 文章nanoid
     * @return 文章
     */
    ArticlePO getByNanoid(@Param("nanoid") String nanoid);
    
    /**
     * 根据slug查询文章
     * @param slug 文章slug
     * @return 文章
     */
    ArticlePO getBySlug(@Param("slug") String slug);
    
    /**
     * 插入文章
     * @param articlePO 文章
     * @return 影响行数
     */
    int insert(ArticlePO articlePO);
    
    /**
     * 更新文章
     * @param articlePO 文章
     * @return 影响行数
     */
    int update(ArticlePO articlePO);
    
    /**
     * 删除文章（逻辑删除）
     * @param nanoid 文章nanoid
     * @return 影响行数
     */
    int delete(@Param("nanoid") String nanoid);
    
    /**
     * 查询文章列表
     * @param title 标题
     * @param content 内容
     * @param categoryNanoid 分类nanoid
     * @param tagNanoid 标签nanoid
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 文章列表
     */
    List<ArticlePO> queryArticles(
            @Param("title") String title,
            @Param("content") String content,
            @Param("categoryNanoid") String categoryNanoid,
            @Param("tagNanoid") String tagNanoid,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}
