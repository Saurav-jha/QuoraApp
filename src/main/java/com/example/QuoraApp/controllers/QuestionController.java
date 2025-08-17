package com.example.QuoraApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDto;
import com.example.QuoraApp.services.IQuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    
    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO)
        .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
        .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDto> getQuestionById(@PathVariable String id ){
        throw new UnsupportedOperationException("Not implemented");
    }

    // cursor based pagination
    @GetMapping()
    public Flux<QuestionResponseDto> getAllQuestions(
        @RequestParam(required = false) String cursor,
        @RequestParam(defaultValue = "10") int size
    ){
        return questionService.getAllQuestions(cursor, size)
        .doOnError(error -> System.out.println("Error fetching questions: " + error))
        .doOnComplete(() -> System.out.println("Questions fetched successfully"));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id){
        throw new UnsupportedOperationException("Not implemented");
    }


    //offset based pagination 
    @GetMapping("/search")
    public Flux<QuestionResponseDto> searchQuestions(
        @RequestParam String query,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return questionService.searchQuestions(query, size, page);
    }

    @GetMapping("/tag/{tag}")
    public Flux<QuestionResponseDto> getQuestionsByTag(
        @PathVariable String tag,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        throw new UnsupportedOperationException("Not implemented");
    }

}
