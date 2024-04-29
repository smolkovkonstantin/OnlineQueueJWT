package org.online.queue.onlinequeuejwt.repository.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.entity.UserSession;
import org.online.queue.onlinequeuejwt.repository.SessionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionRepositoryImpl implements SessionRepository<UserSession> {

    StringRedisTemplate redisTemplate;

    static String SPLITERATOR = ":";

    static String KEY_FORMAT = "%s:%s";
    static String ALL_DEVICES_ID = "*";

    @Override
    public void save(UserSession userSession) {

        var key = getKey(userSession.getUserId(), userSession.getDeviceId());

        redisTemplate.opsForValue().set(key, userSession.getRefreshToken(), userSession.getLifeTime());
    }

    @Override
    public Set<UserSession> findAllByUserId(Long id) {
        var result = redisTemplate.keys(getPatternForAllUserId(id));

        if (Objects.isNull(result)) {
            throw new RuntimeException();
        }

        return result.stream()
                .map(key -> {
                            var userIdAndDeviceId = key.split(SPLITERATOR);
                            var userId = Long.parseLong(userIdAndDeviceId[0]);
                            var deviceId = userIdAndDeviceId[1];
                            return UserSession.builder()
                                    .userId(userId)
                                    .deviceId(deviceId)
                                    .build();
                        }
                )
                .collect(Collectors.toSet());
    }

    @Override
    public UserSession findByUserIdAndDeviceId(Long userId, String deviceId) {
        var key = getKey(userId, deviceId);
        var refreshToken = redisTemplate.opsForValue().get(key);

        return UserSession.builder()
                .userId(userId)
                .deviceId(deviceId)
                .refreshToken(refreshToken)
                .build();
    }


    private String getKey(Long userId, String deviceId) {
        return String.format(KEY_FORMAT, userId, deviceId);
    }

    private String getPatternForAllUserId(Long userId) {
        return String.format(KEY_FORMAT, userId, ALL_DEVICES_ID);
    }
}
