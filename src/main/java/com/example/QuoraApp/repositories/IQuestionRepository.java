package com.example.QuoraApp.repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.example.QuoraApp.models.Question;

import reactor.core.publisher.Flux;


@Repository
public interface IQuestionRepository extends ReactiveMongoRepository<Question, String>{
    
    @Query("{ '$or' : [{'title' : { $regex: ?0, $options: 'i'}} , { 'content' : { $regex: ?0, $options: 'i'}}]}")
    Flux<Question> findByTitleOrContentContainingIgnoreCase(String searchTerm, Pageable pageable);

    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursorTimeStamp, Pageable pageable);

    Flux<Question> findTop10ByOrderByCreatedAtAsc();
}
