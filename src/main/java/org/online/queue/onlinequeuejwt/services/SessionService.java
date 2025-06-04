package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.exceptions.ForbiddenException;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;

public interface SessionService {
    /**
     * Method create session by userId, deviceId, refreshSession and lifeTime
     *
     * @param sessionDto dto with all necessary params
     */
    void create(SessionDto sessionDto);

    /**
     * Method validate user session by userId and refreshToken
     * <br>
     *
     * @param sessionDto input user session
     * @throws ForbiddenException exception throws if current session not equals with session in DB
     */
    void update(SessionDto sessionDto);

    void delete(Long userId);
}
