package com.example.QuoraApp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.Answer;

@Repository
public interface IAnswerRepository extends ReactiveMongoRepository<Answer, String>{
    
}
