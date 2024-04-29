package org.online.queue.onlinequeuejwt.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.enums.ErrorTypeEnum;
import org.online.queue.onlinequeuejwt.exceptions.ForbiddenException;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;
import org.online.queue.onlinequeuejwt.models.entity.UserSession;
import org.online.queue.onlinequeuejwt.repository.SessionRepository;
import org.online.queue.onlinequeuejwt.services.SessionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionServiceImpl implements SessionService {

    SessionRepository<UserSession> sessionRepository;

    @Override
    public void create(SessionDto sessionDto) {

        var session = UserSession.builder()
                .userId(sessionDto.getUserId())
                .deviceId(sessionDto.getDeviceId())
                .refreshToken(sessionDto.getRefreshToken())
                .lifeTime(sessionDto.getLifeTime())
                .build();

        sessionRepository.save(session);
    }

    @Override
    public void validate(SessionDto sessionDto) {
        var userSessionFromDb = sessionRepository.findByUserIdAndDeviceId(sessionDto.getUserId(),
                sessionDto.getDeviceId());

        var currentSession = UserSession.builder()
                .userId(sessionDto.getUserId())
                .deviceId(sessionDto.getDeviceId())
                .refreshToken(sessionDto.getRefreshToken())
                .build();

        if (!userSessionFromDb.equals(currentSession)) {
            throw new ForbiddenException(ErrorTypeEnum.INVALID_SESSION.build());
        }
    }

    @Override
    public void delete(UserSession session) {

    }
}
