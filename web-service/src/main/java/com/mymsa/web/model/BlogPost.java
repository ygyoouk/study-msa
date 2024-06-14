package com.mymsa.web.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@ToString
public class BlogPost {

    @MongoId(value = FieldType.OBJECT_ID)
    private String post_id;

    private String title;
    private String content;
    private Date create_time;

}
