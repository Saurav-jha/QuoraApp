package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDTO question);

    public Flux<QuestionResponseDto> searchQuestions(String searchTerm, int offset, int page);

    public Flux<QuestionResponseDto> getAllQuestions(String cursor, int size);

    public Mono<QuestionResponseDto> getQuestionById(String id);
    
} 
