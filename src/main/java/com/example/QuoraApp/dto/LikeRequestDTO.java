package com.example.QuoraApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.QuoraApp.enums.TargetType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {
    
    @NotBlank(message = "Target ID is required")
    private String targetId;

    @NotBlank(message = "Target type is required")
    private TargetType targetType;

    @NotBlank(message = "Is Like is required")
    private Boolean isLike;
}
