package org.example.jacoryspaceapi.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagPO {
    private Integer id;
    private String nanoid;
    private String name;
    private Date createdAt;
}