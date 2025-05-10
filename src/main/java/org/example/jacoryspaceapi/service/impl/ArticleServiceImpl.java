package org.example.jacoryspaceapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.jacoryspaceapi.common.page.PageDTO;
import org.example.jacoryspaceapi.converter.ArticleConverter;
import org.example.jacoryspaceapi.domain.dto.ArticleDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleQueryDTO;
import org.example.jacoryspaceapi.domain.dto.CategoryDTO;
import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.po.ArticleCategoryPO;
import org.example.jacoryspaceapi.domain.po.ArticlePO;
import org.example.jacoryspaceapi.domain.po.ArticleTagPO;
import org.example.jacoryspaceapi.mapper.ArticleCategoryMapper;
import org.example.jacoryspaceapi.mapper.ArticleMapper;
import org.example.jacoryspaceapi.mapper.ArticleTagMapper;
import org.example.jacoryspaceapi.service.ArticleService;
import org.example.jacoryspaceapi.service.CategoryService;
import org.example.jacoryspaceapi.service.TagService;
import org.example.jacoryspaceapi.util.NanoIdUtil;
import org.example.jacoryspaceapi.util.PageInfoConverter;
import org.example.jacoryspaceapi.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章服务实现类
 * @author Jacory
 * @date 2025/5/10
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;
    
    @Autowired
    private ArticleTagMapper articleTagMapper;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private ArticleConverter articleConverter;
    
    @Override
    public List<ArticleDTO> list() {
        // 1. 查询所有文章
        List<ArticlePO> articlePOList = articleMapper.list();
        List<ArticleDTO> articleDTOList = articleConverter.converterArticlePOListToArticleDTOList(articlePOList);
        
        if (articleDTOList.isEmpty()) {
            return articleDTOList;
        }
        
        // 2. 获取所有文章的nanoid
        List<String> articleNanoids = articleDTOList.stream()
                .map(ArticleDTO::getNanoid)
                .collect(Collectors.toList());
        
        // 3. 批量查询文章-分类关系
        List<ArticleCategoryPO> articleCategoryList = articleCategoryMapper.listByArticleNanoids(articleNanoids);
        Map<String, List<String>> articleCategoryMap = articleCategoryList.stream()
                .collect(Collectors.groupingBy(
                        ArticleCategoryPO::getArticleNanoid,
                        Collectors.mapping(ArticleCategoryPO::getCategoryNanoid, Collectors.toList())
                ));
        
        // 4. 批量查询文章-标签关系
        List<ArticleTagPO> articleTagList = articleTagMapper.listByArticleNanoids(articleNanoids);
        Map<String, List<String>> articleTagMap = articleTagList.stream()
                .collect(Collectors.groupingBy(
                        ArticleTagPO::getArticleNanoid,
                        Collectors.mapping(ArticleTagPO::getTagNanoid, Collectors.toList())
                ));
        
        // 5. 获取所有需要的分类和标签ID
        List<String> categoryNanoids = articleCategoryList.stream()
                .map(ArticleCategoryPO::getCategoryNanoid)
                .distinct()
                .collect(Collectors.toList());
        
        List<String> tagNanoids = articleTagList.stream()
                .map(ArticleTagPO::getTagNanoid)
                .distinct()
                .collect(Collectors.toList());
        
        // 6. 批量查询分类和标签
        Map<String, CategoryDTO> categoryMap = Collections.emptyMap();
        if (!categoryNanoids.isEmpty()) {
            List<CategoryDTO> categoryDTOList = categoryService.listByNanoids(categoryNanoids);
            categoryMap = categoryDTOList.stream()
                    .collect(Collectors.toMap(CategoryDTO::getNanoid, Function.identity()));
        }
        
        Map<String, TagDTO> tagMap = Collections.emptyMap();
        if (!tagNanoids.isEmpty()) {
            List<TagDTO> tagDTOList = tagService.listByNanoids(tagNanoids);
            tagMap = tagDTOList.stream()
                    .collect(Collectors.toMap(TagDTO::getNanoid, Function.identity()));
        }
        
        // 7. 填充文章的分类和标签
        articleConverter.fillArticleDTOListRelations(
                articleDTOList, categoryMap, tagMap, articleCategoryMap, articleTagMap);
        
        return articleDTOList;
    }
    
    @Override
    public ArticleDTO getByNanoid(String nanoid) {
        // 1. 查询文章
        ArticlePO articlePO = articleMapper.getByNanoid(nanoid);
        if (articlePO == null) {
            return null;
        }
        
        // 2. 转换为DTO
        ArticleDTO articleDTO = articleConverter.converterArticlePOtoArticleDTO(articlePO);
        
        // 3. 查询分类和标签
        List<String> categoryNanoids = articleCategoryMapper.listCategoryNanoidsByArticleNanoid(nanoid);
        List<String> tagNanoids = articleTagMapper.listTagNanoidsByArticleNanoid(nanoid);
        
        // 4. 查询分类和标签详情
        if (!categoryNanoids.isEmpty()) {
            List<CategoryDTO> categoryDTOList = categoryService.listByNanoids(categoryNanoids);
            articleDTO.setCategories(categoryDTOList);
        }
        
        if (!tagNanoids.isEmpty()) {
            List<TagDTO> tagDTOList = tagService.listByNanoids(tagNanoids);
            articleDTO.setTags(tagDTOList);
        }
        
        return articleDTO;
    }
    
    @Override
    public ArticleDTO getBySlug(String slug) {
        // 1. 查询文章
        ArticlePO articlePO = articleMapper.getBySlug(slug);
        if (articlePO == null) {
            return null;
        }
        
        // 2. 转换为DTO
        ArticleDTO articleDTO = articleConverter.converterArticlePOtoArticleDTO(articlePO);
        
        // 3. 查询分类和标签
        String nanoid = articlePO.getNanoid();
        List<String> categoryNanoids = articleCategoryMapper.listCategoryNanoidsByArticleNanoid(nanoid);
        List<String> tagNanoids = articleTagMapper.listTagNanoidsByArticleNanoid(nanoid);
        
        // 4. 查询分类和标签详情
        if (!categoryNanoids.isEmpty()) {
            List<CategoryDTO> categoryDTOList = categoryService.listByNanoids(categoryNanoids);
            articleDTO.setCategories(categoryDTOList);
        }
        
        if (!tagNanoids.isEmpty()) {
            List<TagDTO> tagDTOList = tagService.listByNanoids(tagNanoids);
            articleDTO.setTags(tagDTOList);
        }
        
        return articleDTO;
    }
    
    @Override
    @Transactional
    public ArticleDTO create(ArticleDTO articleDTO) {
        // 1. 生成nanoid和slug
        String nanoid = "art_" + NanoIdUtil.randomNanoId();
        String slug = SlugUtil.generateSlug(articleDTO.getTitle());
        
        // 2. 转换为PO
        ArticlePO articlePO = new ArticlePO();
        articlePO.setNanoid(nanoid);
        articlePO.setSlug(slug);
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setExcerpt(articleDTO.getExcerpt());
        articlePO.setContent(articleDTO.getContent());
        articlePO.setCoverImage(articleDTO.getCoverImage());
        articlePO.setReadingTime(articleDTO.getReadingTime());
        articlePO.setPublishedDate(articleDTO.getPublishedDate());
        articlePO.setIsDeleted(0);
        
        // 3. 插入文章
        articleMapper.insert(articlePO);
        
        // 4. 处理分类关联
        List<CategoryDTO> categories = articleDTO.getCategories();
        if (categories != null && !categories.isEmpty()) {
            List<ArticleCategoryPO> articleCategoryList = categories.stream()
                    .map(category -> new ArticleCategoryPO(nanoid, category.getNanoid()))
                    .collect(Collectors.toList());
            articleCategoryMapper.batchInsert(articleCategoryList);
        }
        
        // 5. 处理标签关联
        List<TagDTO> tags = articleDTO.getTags();
        if (tags != null && !tags.isEmpty()) {
            List<ArticleTagPO> articleTagList = tags.stream()
                    .map(tag -> new ArticleTagPO(nanoid, tag.getNanoid()))
                    .collect(Collectors.toList());
            articleTagMapper.batchInsert(articleTagList);
        }
        
        // 6. 返回创建后的文章
        return getByNanoid(nanoid);
    }
    
    @Override
    @Transactional
    public ArticleDTO update(ArticleDTO articleDTO) {
        String nanoid = articleDTO.getNanoid();
        
        // 1. 查询原文章
        ArticlePO originalArticle = articleMapper.getByNanoid(nanoid);
        if (originalArticle == null) {
            return null;
        }
        
        // 2. 更新文章基本信息
        ArticlePO articlePO = new ArticlePO();
        articlePO.setNanoid(nanoid);
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setExcerpt(articleDTO.getExcerpt());
        articlePO.setContent(articleDTO.getContent());
        articlePO.setCoverImage(articleDTO.getCoverImage());
        articlePO.setReadingTime(articleDTO.getReadingTime());
        articlePO.setPublishedDate(articleDTO.getPublishedDate());
        
        // 如果标题变了，重新生成slug
        if (!originalArticle.getTitle().equals(articleDTO.getTitle())) {
            articlePO.setSlug(SlugUtil.generateSlug(articleDTO.getTitle()));
        }
        
        // 3. 更新文章
        articleMapper.update(articlePO);
        
        // 4. 更新分类关联
        articleCategoryMapper.deleteByArticleNanoid(nanoid);
        List<CategoryDTO> categories = articleDTO.getCategories();
        if (categories != null && !categories.isEmpty()) {
            List<ArticleCategoryPO> articleCategoryList = categories.stream()
                    .map(category -> new ArticleCategoryPO(nanoid, category.getNanoid()))
                    .collect(Collectors.toList());
            articleCategoryMapper.batchInsert(articleCategoryList);
        }
        
        // 5. 更新标签关联
        articleTagMapper.deleteByArticleNanoid(nanoid);
        List<TagDTO> tags = articleDTO.getTags();
        if (tags != null && !tags.isEmpty()) {
            List<ArticleTagPO> articleTagList = tags.stream()
                    .map(tag -> new ArticleTagPO(nanoid, tag.getNanoid()))
                    .collect(Collectors.toList());
            articleTagMapper.batchInsert(articleTagList);
        }
        
        // 6. 返回更新后的文章
        return getByNanoid(nanoid);
    }
    
    @Override
    @Transactional
    public boolean delete(String nanoid) {
        // 1. 逻辑删除文章
        int result = articleMapper.delete(nanoid);
        
        // 2. 删除关联关系（物理删除）
        articleCategoryMapper.deleteByArticleNanoid(nanoid);
        articleTagMapper.deleteByArticleNanoid(nanoid);
        
        return result > 0;
    }
    
    @Override
    public PageDTO<ArticleDTO> queryArticles(ArticleQueryDTO queryDTO) {
        if (queryDTO.isFetchAll()) {
            // 不分页查询
            List<ArticlePO> articles = articleMapper.queryArticles(
                    queryDTO.getTitle(),
                    queryDTO.getContent(),
                    queryDTO.getCategoryNanoid(),
                    queryDTO.getTagNanoid(),
                    queryDTO.getStartDate(),
                    queryDTO.getEndDate()
            );
            
            // 转换为DTO
            List<ArticleDTO> articleDTOList = articleConverter.converterArticlePOListToArticleDTOList(articles);
            
            // 填充分类和标签
            fillArticleDTOListWithCategoriesAndTags(articleDTOList);
            
            return new PageDTO<>(articleDTOList.size(), 1, articleDTOList);
        } else {
            // 分页查询
            PageInfo<ArticlePO> articlePage;
            try (Page<ArticlePO> page = PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize())) {
                articlePage = page.doSelectPageInfo(() -> articleMapper.queryArticles(
                        queryDTO.getTitle(),
                        queryDTO.getContent(),
                        queryDTO.getCategoryNanoid(),
                        queryDTO.getTagNanoid(),
                        queryDTO.getStartDate(),
                        queryDTO.getEndDate()
                ));
            }
            
            // 转换为DTO
            List<ArticleDTO> articleDTOList = articleConverter.converterArticlePOListToArticleDTOList(articlePage.getList());
            
            // 填充分类和标签
            fillArticleDTOListWithCategoriesAndTags(articleDTOList);
            
            return PageInfoConverter.convertPageInfoToPageDTO(articlePage, articleDTOList);
        }
    }
    
    /**
     * 填充文章DTO列表的分类和标签
     * @param articleDTOList 文章DTO列表
     */
    private void fillArticleDTOListWithCategoriesAndTags(List<ArticleDTO> articleDTOList) {
        if (articleDTOList == null || articleDTOList.isEmpty()) {
            return;
        }
        
        // 获取所有文章nanoid
        List<String> articleNanoids = articleDTOList.stream()
                .map(ArticleDTO::getNanoid)
                .collect(Collectors.toList());
        
        // 查询文章-分类关联
        List<ArticleCategoryPO> articleCategoryPOList = articleCategoryMapper.listByArticleNanoids(articleNanoids);
        
        // 查询文章-标签关联
        List<ArticleTagPO> articleTagPOList = articleTagMapper.listByArticleNanoids(articleNanoids);
        
        // 构建文章-分类关系Map
        Map<String, List<String>> articleCategoryMap = articleCategoryPOList.stream()
                .collect(Collectors.groupingBy(
                        ArticleCategoryPO::getArticleNanoid,
                        Collectors.mapping(ArticleCategoryPO::getCategoryNanoid, Collectors.toList())
                ));
        
        // 构建文章-标签关系Map
        Map<String, List<String>> articleTagMap = articleTagPOList.stream()
                .collect(Collectors.groupingBy(
                        ArticleTagPO::getArticleNanoid,
                        Collectors.mapping(ArticleTagPO::getTagNanoid, Collectors.toList())
                ));
        
        // 获取所有分类nanoid
        List<String> categoryNanoids = articleCategoryPOList.stream()
                .map(ArticleCategoryPO::getCategoryNanoid)
                .distinct()
                .collect(Collectors.toList());
        
        // 获取所有标签nanoid
        List<String> tagNanoids = articleTagPOList.stream()
                .map(ArticleTagPO::getTagNanoid)
                .distinct()
                .collect(Collectors.toList());
        
        // 查询所有分类
        List<CategoryDTO> categoryDTOList = categoryService.listByNanoids(categoryNanoids);
        
        // 查询所有标签
        List<TagDTO> tagDTOList = tagService.listByNanoids(tagNanoids);
        
        // 构建分类Map
        Map<String, CategoryDTO> categoryMap = categoryDTOList.stream()
                .collect(Collectors.toMap(CategoryDTO::getNanoid, category -> category));
        
        // 构建标签Map
        Map<String, TagDTO> tagMap = tagDTOList.stream()
                .collect(Collectors.toMap(TagDTO::getNanoid, tag -> tag));
        
        // 填充文章DTO的分类和标签
        articleConverter.fillArticleDTOListRelations(articleDTOList, categoryMap, tagMap, articleCategoryMap, articleTagMap);
    }
}
