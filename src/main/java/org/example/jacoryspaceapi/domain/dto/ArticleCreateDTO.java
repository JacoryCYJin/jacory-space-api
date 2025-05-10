package org.example.jacoryspaceapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleCreateDTO {
    private String title;
    private String excerpt;
    private String content;
    private String coverImage;
    private Date publishedDate;
    private List<String> categoryNanoids;
    private List<String> tagNanoids;
}