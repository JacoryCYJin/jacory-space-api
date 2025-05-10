package org.example.jacoryspaceapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private String nanoid;
    private String name;
    private String description;
}