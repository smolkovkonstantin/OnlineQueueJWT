package org.online.queue.onlinequeuejwt.models.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Objects;

@Builder
@Getter
@Setter
public class UserSession {
    String refreshToken;
    Long userId;
    String deviceId;
    Duration lifeTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(refreshToken, that.refreshToken) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refreshToken, userId, deviceId);
    }
}
