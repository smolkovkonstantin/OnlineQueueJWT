package org.online.queue.onlinequeuejwt.repository;

import org.online.queue.onlinequeuejwt.models.entity.UserSession;

import java.util.List;
import java.util.Set;

public interface SessionRepository {

    void save(UserSession userSession);

    /**
     * @param userId id user from DB
     * @return {@link Set} all session for user
     */
    List<UserSession> findAllByUserId(Long userId);

    /**
     * @param userId id user from DB
     * @param deviceId user device id
     * @return {@link UserSession} all data about user session
     */
    UserSession findByUserIdAndDeviceIdAndDelete(Long userId, String deviceId);

    UserSession findByUserIdAndDeviceId(Long userId, String deviceId);

    void deleteAll(List<UserSession> userSessions);
}
