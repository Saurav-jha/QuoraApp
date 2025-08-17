package com.example.QuoraApp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.Like;

@Repository
public interface ILikeRepository extends ReactiveMongoRepository<Like, String>{
    
} 
