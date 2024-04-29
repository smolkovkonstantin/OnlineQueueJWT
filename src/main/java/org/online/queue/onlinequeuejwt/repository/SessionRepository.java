package org.online.queue.onlinequeuejwt.repository;

import org.online.queue.onlinequeuejwt.models.entity.UserSession;

import java.util.List;
import java.util.Set;

public interface SessionRepository<T> {

    void save(UserSession userSession);

    Set<T> findAllByUserId(Long userId);

    T findByUserIdAndDeviceId(Long userId, String deviceId);
}
