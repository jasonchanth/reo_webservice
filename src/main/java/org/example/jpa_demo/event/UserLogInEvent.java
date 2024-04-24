package org.example.jpa_demo.event;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.entity.User;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class UserLogInEvent extends ApplicationEvent {
    private final User user;
    private String fcmToken;

    public UserLogInEvent(User user) {
        super(user); // Use the user object as the source
        this.user = user;
    }
}