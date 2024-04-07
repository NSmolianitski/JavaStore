package co.baboon.store.users;

import co.baboon.store.roles.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record User(Long id, String username, String password, Set<Role> roles) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static Builder builder() { return new Builder(); }
    
    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private Set<Role> roles = new HashSet<>();

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }
        
        public Builder copyFrom(User user) {
            this.id = user.id;
            this.username = user.username;
            this.password = user.password;
            this.roles = user.roles;
            return this;
        }

        public User build() {
            return new User(id, username, password, roles);
        }
    }
}
