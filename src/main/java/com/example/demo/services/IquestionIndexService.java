package com.example.demo.services;

import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import reactor.core.publisher.Mono;

public interface IquestionIndexService {

    void createQuestionIndex (Question questionElasticDocument);


}
