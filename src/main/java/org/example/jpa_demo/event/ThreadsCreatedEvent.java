package org.example.jpa_demo.event;

import lombok.Getter;
import org.example.jpa_demo.entity.Threads;
import org.springframework.context.ApplicationEvent;

@Getter
public class ThreadsCreatedEvent extends ApplicationEvent {
    private final Threads threads;

    public ThreadsCreatedEvent(Object source, Threads threads) {
        super(source);
        this.threads = threads;
    }

}
