package com.example.QuoraApp.events;

import java.time.LocalDateTime;

import com.example.QuoraApp.enums.TargetType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCountEvent {
    private String targetId;
    private TargetType targetType;
    private LocalDateTime timestamp;
}
