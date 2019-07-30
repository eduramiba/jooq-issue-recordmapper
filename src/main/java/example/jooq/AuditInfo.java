package example.jooq;

import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;
import java.util.Objects;

public class AuditInfo {

    private final User user;
    private final OffsetDateTime timestamp;

    @ConstructorProperties({"user", "timestamp"})
    public AuditInfo(User user, OffsetDateTime timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.timestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuditInfo other = (AuditInfo) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private User user;
        private OffsetDateTime timestamp;

        private Builder() {
        }

        public Builder user(final User value) {
            this.user = value;
            return this;
        }

        public Builder timestamp(final OffsetDateTime value) {
            this.timestamp = value;
            return this;
        }

        public AuditInfo build() {
            return new example.jooq.AuditInfo(user, timestamp);
        }
    }

    public static AuditInfo.Builder builder() {
        return new AuditInfo.Builder();
    }
}
