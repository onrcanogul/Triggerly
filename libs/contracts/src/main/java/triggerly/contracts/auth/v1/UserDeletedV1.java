package triggerly.contracts.auth.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record UserDeletedV1(
        @JsonProperty("userId") String userId,
        @JsonProperty("email") String email,
        @JsonProperty("deletedAt") Instant deletedAt
) {
}

