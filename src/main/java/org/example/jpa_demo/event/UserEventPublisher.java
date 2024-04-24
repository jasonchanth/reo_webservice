package org.example.jpa_demo.event;

import org.example.jpa_demo.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component
public class UserEventPublisher {
    private final ApplicationEventPublisher eventPublisher;
    private static final Log logger = LogFactory.getLog(UserEventPublisher.class);


    public UserEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUserLoginEvent(User user) {
        logger.info("publishUserLoginEvent start");
        UserLogInEvent event = new UserLogInEvent(user);
        eventPublisher.publishEvent(event);
    }
}