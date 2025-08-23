package com.example.demo.services;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;

import com.example.demo.models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {
    
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size);

    public Flux<QuestionResponseDTO> getAllQuestion(String cursor, int size);

    public List<QuestionElasticDocument> searchQuestionsByElasticsearch(String query);
}
