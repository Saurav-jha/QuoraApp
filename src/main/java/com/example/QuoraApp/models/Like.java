package com.example.QuoraApp.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.QuoraApp.enums.TargetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "likes")
public class Like {
    @Id
    private String id;

    private String targetId;

    private TargetType targetType; // QUESTION, ANSWER

    private Boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
