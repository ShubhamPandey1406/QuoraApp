package com.example.demo.services;

import java.time.LocalDateTime;

import com.example.demo.utils.CursorUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.adapter.QuestionAdapter;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;
import com.example.demo.repositories.QuestionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
            .title(questionRequestDTO.getTitle())
            .content(questionRequestDTO.getContent())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        return questionRepository.save(question)
        .map(QuestionAdapter::toQuestionResponseDTO)
        .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
        .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response-> System.out.println("Question is " + response))
                .doOnError(error->System.out.println("No question found "+error))
                .switchIfEmpty(Mono.error(new RuntimeException("Question not found with id: " + id)));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size) {

        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm,PageRequest.of(page,size))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error->System.out.println("Error searching questions" + error))
               .doOnComplete(()->System.out.println("Questions searched successfully"));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestion(String cursor, int size) {
        Pageable pageable=PageRequest.of(0,size);
        if(!CursorUtils.isValidCursor(cursor))
        {
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error->System.out.println("Error searching questions" + error))
                    .doOnComplete(()->System.out.println("Questions searched"));

        }
        else
        {
            LocalDateTime cursorTimeStamp=CursorUtils.parseCursor(cursor);
           return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp,pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error->System.out.println("Error searching questions" + error))
                    .doOnComplete(()->System.out.println("Questions searched successfully"));
        }

        }




}
