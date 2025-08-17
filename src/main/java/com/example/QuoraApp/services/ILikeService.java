package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.LikeRequestDTO;
import com.example.QuoraApp.dto.LikeResponseDTO;
import com.example.QuoraApp.enums.TargetType;

import reactor.core.publisher.Mono;

public interface ILikeService {
    
    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO> countLikeByTargetIdAndTragetType(String targetId, TargetType targetType);
    Mono<LikeResponseDTO> countDisLikeByTargetIdAndTargetType(String targetId, TargetType targetType);
    Mono<LikeResponseDTO> toggleLike(String targetId, TargetType targetType, Boolean isLike);
}
