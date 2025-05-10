package org.example.jacoryspaceapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagDTO {
    private Integer id;
    private String nanoid;
    private String name;
    private Date createdAt;
}