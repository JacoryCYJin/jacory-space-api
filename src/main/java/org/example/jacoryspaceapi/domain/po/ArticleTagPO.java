package org.example.jacoryspaceapi.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章-标签关联持久化对象
 * @author Jacory
 * @date 2025/5/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagPO {
    private String articleNanoid;
    private String tagNanoid;
}