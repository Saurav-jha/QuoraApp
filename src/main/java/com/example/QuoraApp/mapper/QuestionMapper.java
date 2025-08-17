package com.example.QuoraApp.mapper;

import com.example.QuoraApp.dto.QuestionResponseDto;
import com.example.QuoraApp.models.Question;

public class QuestionMapper {
    
    public static QuestionResponseDto toQuestionResponseDto(Question question) {
        return QuestionResponseDto.builder()
            .id(question.getId())
            .title(question.getTitle())
            .content(question.getContent())
            .createdAt(question.getCreatedAt())
            .build();
    }
}
