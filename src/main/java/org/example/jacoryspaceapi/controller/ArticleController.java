package org.example.jacoryspaceapi.controller;

import org.example.jacoryspaceapi.common.page.PageDTO;
import org.example.jacoryspaceapi.converter.ArticleConverter;
import org.example.jacoryspaceapi.domain.dto.ArticleDTO;
import org.example.jacoryspaceapi.domain.dto.ArticleQueryDTO;
import org.example.jacoryspaceapi.domain.vo.ArticleVO;
import org.example.jacoryspaceapi.common.Result;
import org.example.jacoryspaceapi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章控制器
 * @author Jacory
 * @date 2025/5/10
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleConverter articleConverter;
    
    /**
     * 查询所有文章
     * @return 文章列表
     */
    @GetMapping("/list")
    public Result<List<ArticleVO>> list() {
        List<ArticleDTO> articleDTOList = articleService.list();
        List<ArticleVO> articleVOList = articleConverter.converterArticleDTOListToArticleVOList(articleDTOList);
        return Result.success(articleVOList);
    }
    
    /**
     * 根据nanoid查询文章
     * @param nanoid 文章nanoid
     * @return 文章
     */
    @GetMapping("/{nanoid}")
    public Result<ArticleVO> getByNanoid(@PathVariable String nanoid) {
        ArticleDTO articleDTO = articleService.getByNanoid(nanoid);
        if (articleDTO == null) {
            return Result.fail("文章不存在");
        }
        ArticleVO articleVO = articleConverter.converterArticleDTOtoArticleVO(articleDTO);
        return Result.success(articleVO);
    }
    
    /**
     * 根据slug查询文章
     * @param slug 文章slug
     * @return 文章
     */
    @GetMapping("/slug/{slug}")
    public Result<ArticleVO> getBySlug(@PathVariable String slug) {
        ArticleDTO articleDTO = articleService.getBySlug(slug);
        if (articleDTO == null) {
            return Result.fail("文章不存在");
        }
        ArticleVO articleVO = articleConverter.converterArticleDTOtoArticleVO(articleDTO);
        return Result.success(articleVO);
    }
    
    /**
     * 创建文章
     * @param articleDTO 文章
     * @return 创建后的文章
     */
    @PostMapping
    public Result<ArticleVO> create(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO createdArticle = articleService.create(articleDTO);
        ArticleVO articleVO = articleConverter.converterArticleDTOtoArticleVO(createdArticle);
        return Result.success("创建成功", articleVO);
    }
    
    /**
     * 更新文章
     * @param articleDTO 文章
     * @return 更新后的文章
     */
    @PutMapping
    public Result<ArticleVO> update(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO updatedArticle = articleService.update(articleDTO);
        if (updatedArticle == null) {
            return Result.fail("文章不存在");
        }
        ArticleVO articleVO = articleConverter.converterArticleDTOtoArticleVO(updatedArticle);
        return Result.success("更新成功", articleVO);
    }
    
    /**
     * 删除文章
     * @param nanoid 文章nanoid
     * @return 是否成功
     */
    @DeleteMapping("/{nanoid}")
    public Result<Void> delete(@PathVariable String nanoid) {
        boolean success = articleService.delete(nanoid);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }
    
    /**
     * 查询文章列表
     * @param title 标题
     * @param content 内容
     * @param categoryNanoid 分类nanoid
     * @param tagNanoid 标签nanoid
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param fetchAll 是否查询全部
     * @return 分页结果
     */
    @GetMapping("/query")
    public Result<PageDTO<ArticleVO>> queryArticles(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String categoryNanoid,
            @RequestParam(required = false) String tagNanoid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Boolean fetchAll
    ) {
        // 构建查询DTO
        ArticleQueryDTO queryDTO = ArticleQueryDTO.of(
                title, content, categoryNanoid, tagNanoid, startDate, endDate, pageNum, pageSize, fetchAll
        );
        
        // 查询文章
        PageDTO<ArticleDTO> pageDTO = articleService.queryArticles(queryDTO);
        
        // 转换为VO
        List<ArticleVO> articleVOList = pageDTO.getList().stream()
                .map(articleConverter::converterArticleDTOtoArticleVO)
                .collect(Collectors.toList());
        
        // 构建分页结果
        PageDTO<ArticleVO> resultPage = new PageDTO<>(pageDTO.getTotal(), pageDTO.getPages(), articleVOList);
        
        return Result.success(resultPage);
    }
}
