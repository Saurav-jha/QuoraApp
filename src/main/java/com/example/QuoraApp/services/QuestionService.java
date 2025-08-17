package com.example.QuoraApp.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDto;
import com.example.QuoraApp.mapper.QuestionMapper;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repositories.IQuestionRepository;
import com.example.QuoraApp.utils.CursorUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final IQuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDTO questionRequestDTO) {
        
        Question question = Question.builder()
            .title(questionRequestDTO.getTitle())
            .content(questionRequestDTO.getContent())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        return questionRepository.save(question)
        .map(QuestionMapper::toQuestionResponseDto)
        .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
        .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @Override
    public Flux<QuestionResponseDto> searchQuestions(String searchTerm, int offset, int page){
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, page))
        .map(QuestionMapper:: toQuestionResponseDto)
        .doOnError(error -> System.out.println("Error searching questions:" + error))
        .doOnComplete(() -> System.out.println("Questions searched successfully"));
    }

    @Override
    public Flux<QuestionResponseDto> getAllQuestions(String cursor, int size){
        Pageable pageable = PageRequest.of(0, size);
        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
            .take(size)
            .map(QuestionMapper :: toQuestionResponseDto)
            .doOnError(error -> System.out.println("Error fetching questions: " + error))
            .doOnComplete(() -> System.out.println("Questions fetched successfully"));
        }else{
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
            .map(QuestionMapper :: toQuestionResponseDto)
            .doOnError(error -> System.out.println("Error fetching questions: " + error))
            .doOnComplete(() -> System.out.println("Questions fetched successfully"));
        }
    }


    public Flux<QuestionResponseDto> getAllQuestionsByTitle(){
        return questionRepository.findAll()
        .sort((q1, q2) -> q1.getTitle().compareToIgnoreCase(q2.getTitle()))
        .map(QuestionMapper::toQuestionResponseDto)
        .doOnError(error -> System.out.println("Error fetching questions by title: " + error))
        .doOnComplete(() -> System.out.println("Questions by title fetched successfully"));
    }

    @Override
    public Mono<QuestionResponseDto> getQuestionById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'getQuestionById'");
    }
    
}
