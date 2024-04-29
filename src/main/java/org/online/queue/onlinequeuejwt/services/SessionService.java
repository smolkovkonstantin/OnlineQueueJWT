package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.dto.SessionDto;
import org.online.queue.onlinequeuejwt.models.entity.UserSession;

public interface SessionService {
    void create(SessionDto sessionDto);

    void validate(SessionDto sessionDto);

    void delete(UserSession session);
}
