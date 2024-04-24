package org.example.jpa_demo.event;

import org.example.jpa_demo.entity.Threads;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ThreadsEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public ThreadsEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishThreadsCreatedEvent(Threads threads) {
        ThreadsCreatedEvent event = new ThreadsCreatedEvent(this, threads);
        eventPublisher.publishEvent(event);
    }
}