package org.example.jacoryspaceapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryVO {
    private String nanoid;
    private String name;
    private String description;
}