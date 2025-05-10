package org.example.jacoryspaceapi.service;

import org.example.jacoryspaceapi.common.page.PageDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleQueryDTO;

import java.util.List;

/**
 * 文章服务接口
 * @author Jacory
 * @date 2025/5/10
 */
public interface ArticleService {
    
    /**
     * 查询所有文章
     * @return 文章列表
     */
    List<ArticleDTO> list();
    
    /**
     * 根据nanoid查询文章
     * @param nanoid 文章nanoid
     * @return 文章
     */
    ArticleDTO getByNanoid(String nanoid);
    
    /**
     * 根据slug查询文章
     * @param slug 文章slug
     * @return 文章
     */
    ArticleDTO getBySlug(String slug);
    
    /**
     * 创建文章
     * @param articleDTO 文章
     * @return 创建后的文章
     */
    ArticleDTO create(ArticleDTO articleDTO);
    
    /**
     * 更新文章
     * @param articleDTO 文章
     * @return 更新后的文章
     */
    ArticleDTO update(ArticleDTO articleDTO);
    
    /**
     * 删除文章
     * @param nanoid 文章nanoid
     * @return 是否成功
     */
    boolean delete(String nanoid);
    
    /**
     * 查询文章列表
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    PageDTO<ArticleDTO> queryArticles(ArticleQueryDTO queryDTO);
}
