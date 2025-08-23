package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO {

    private String id;

    @NotBlank(message = "Content is required")
    @Size(min = 10,max=1000, message = "Content must be between 10 and 1000 characters")
    private String content;

    private String questionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
