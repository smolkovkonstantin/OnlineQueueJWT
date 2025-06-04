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

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;

    @Override
    public void create(SessionDto sessionDto) {

        var userSessions = sessionRepository.findAllByUserId(sessionDto.getUserId());

        if (userSessions.size() > 2) {
            dropAllSessions(userSessions);
        } else {

            var session = UserSession.builder()
                    .userId(sessionDto.getUserId())
                    .deviceId(sessionDto.getDeviceId())
                    .refreshToken(sessionDto.getRefreshToken())
                    .lifeTime(sessionDto.getLifeTime())
                    .build();

            sessionRepository.save(session);
        }
    }

    private void dropAllSessions(List<UserSession> userSessions) {
        sessionRepository.deleteAll(userSessions);
    }

    @Override
    public void update(SessionDto sessionDto) {
        var userSessionFromDb = sessionRepository.findByUserIdAndDeviceIdAndDelete(sessionDto.getUserId(),
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
    public void delete(Long userId) {
        throw new UnsupportedOperationException();
    }
}
