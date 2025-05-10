package org.example.jacoryspaceapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO {
    private Integer id;
    private String nanoid;
    private String slug;
    private String title;
    private String excerpt;
    private String content;
    private String coverImage;
    private String readingTime;
    private Date publishedDate;
    private Date createdAt;
    private Date updatedAt;
    
    // 关联的分类和标签
    private List<CategoryDTO> categories;
    private List<TagDTO> tags;
}