package org.online.queue.onlinequeuejwt.models.api.request;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class DeleteRequest {
    Long userId;
}
