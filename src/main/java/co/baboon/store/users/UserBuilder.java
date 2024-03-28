package co.baboon.store.users;

public class UserBuilder {
    private Long id;
    private String login;
    private String password;

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(id, login, password);
    }
}