package org.example.jacoryspaceapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkVO {
    private String nanoid;
    private String title;
    private String description;
    private String coverImage;
    private String link;
    private Date launchDate;
    private Integer status;
    private String mood;

    private List<TagVO> tags;
}
