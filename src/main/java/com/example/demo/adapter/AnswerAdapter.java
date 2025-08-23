package com.example.demo.adapter;

import com.example.demo.dto.AnswerResponseDTO;
import com.example.demo.models.Answer;

public class AnswerAdapter {
    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer) {

        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestionId())
                .createdAt(answer.getCreatedAt()).build();

    }

}
