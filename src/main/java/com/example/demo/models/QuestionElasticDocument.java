package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="questions")
public class QuestionElasticDocument {

    private String id;

    private String title;

    private String content;

}
