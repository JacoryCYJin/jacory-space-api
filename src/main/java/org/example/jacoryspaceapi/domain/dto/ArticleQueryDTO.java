package org.example.jacoryspaceapi.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.jacoryspaceapi.common.page.PageQueryDTO;

import java.util.Date;

/**
 * 文章查询DTO
 * @author Jacory
 * @date 2025/5/10
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleQueryDTO extends PageQueryDTO {
    private String title;
    private String content;
    private String categoryNanoid;
    private String tagNanoid;
    private Date startDate;
    private Date endDate;

    public static ArticleQueryDTO of(String title, String content, String categoryNanoid, String tagNanoid, 
                                    Date startDate, Date endDate, Integer pageNum, Integer pageSize, Boolean fetchAll) {
        ArticleQueryDTO dto = new ArticleQueryDTO();
        dto.setPageNum(pageNum);
        dto.setPageSize(pageSize);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setCategoryNanoid(categoryNanoid);
        dto.setTagNanoid(tagNanoid);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setFetchAll(fetchAll);
        dto.validPageParam();
        return dto;
    }
}