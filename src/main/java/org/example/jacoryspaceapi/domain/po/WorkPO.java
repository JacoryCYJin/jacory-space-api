package org.example.jacoryspaceapi.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPO {
    private Integer id;
    private String nanoid;
    private String title;
    private String description;
    private String coverImage;
    private String link;
    private Date launchDate;
    private Integer status;
    private String mood;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
}
