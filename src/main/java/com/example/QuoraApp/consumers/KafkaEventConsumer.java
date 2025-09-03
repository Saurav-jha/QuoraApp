package com.example.QuoraApp.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.config.KafkaConfig;
import com.example.QuoraApp.events.ViewCountEvent;
import com.example.QuoraApp.repositories.IQuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final IQuestionRepository questionRepository;

    @KafkaListener(
        topics = KafkaConfig.TPOIC_NAME,
        groupId = "view-count-group",
        containerFactory = "kafkaListenerContainerFactory")
    public void handleViewCountEvent(ViewCountEvent event){
        questionRepository.findById(event.getTargetId())
        .flatMap(question -> {
            System.out.println("Incrementing view count for question: " + question.getId());
            Integer views = question.getViews();
            question.setViews(views == null ? 0 : views + 1);
            return questionRepository.save(question);
        })
        .subscribe(updatedQuestion -> {
            System.out.println("View count incremented for question: " + updatedQuestion.getId());
        }, error -> {
            System.out.println("Error incrementing view count for question: "+ error.getMessage());
        });
    }
}
