package example.jooq;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class ThingModel {

    private final Integer id;
    private final String description;
    private final AuditInfo auditInfo;

    @ConstructorProperties({"id", "description", "auditInfo"})
    public ThingModel(Integer id, String description, AuditInfo auditInfo) {
        this.id = id;
        this.description = description;
        this.auditInfo = auditInfo;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.auditInfo);
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
        final ThingModel other = (ThingModel) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.auditInfo, other.auditInfo)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private Integer id;
        private String description;
        private AuditInfo auditInfo;

        private Builder() {
        }

        public Builder id(final Integer value) {
            this.id = value;
            return this;
        }

        public Builder description(final String value) {
            this.description = value;
            return this;
        }

        public Builder auditInfo(final AuditInfo value) {
            this.auditInfo = value;
            return this;
        }

        public ThingModel build() {
            return new example.jooq.ThingModel(id, description, auditInfo);
        }
    }

    public static ThingModel.Builder builder() {
        return new ThingModel.Builder();
    }

}
