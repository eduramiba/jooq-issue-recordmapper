package example.jooq;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class User {

    private final Integer id;
    private final String name;

    @ConstructorProperties({"id", "name"})
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private Integer id;
        private String name;

        private Builder() {
        }

        public Builder id(final Integer value) {
            this.id = value;
            return this;
        }

        public Builder name(final String value) {
            this.name = value;
            return this;
        }

        public User build() {
            return new example.jooq.User(id, name);
        }
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

}
