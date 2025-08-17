package com.example.QuoraApp.dto;

import java.time.LocalDateTime;

import com.example.QuoraApp.enums.TargetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {
    
    private String id;

    private String targetId;

    private TargetType targetType;

    private Boolean isLike;

    private LocalDateTime createdAt;

}
