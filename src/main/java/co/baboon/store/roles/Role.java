package co.baboon.store.roles;

import org.springframework.security.core.GrantedAuthority;

public record Role(Long id, String name) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return name();
    }

    public static Builder builder() { return new Builder(); }
    
    public static class Builder {
        private Long id;
        private String name;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder copyFrom(Role role) {
            this.id = role.id();
            this.name = role.name();
            return this;
        }

        public Role build() {
            return new Role(id, name);
        }
    }
}
