package org.example.jacoryspaceapi.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticlePO {
    private Integer id;
    private String nanoid;
    private String slug;
    private String title;
    private String excerpt;
    private String content;
    private String coverImage;
    private String readingTime;
    private Date publishedDate;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
}