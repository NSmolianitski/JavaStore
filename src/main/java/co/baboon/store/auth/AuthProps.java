package co.baboon.store.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "store.auth")
public class AuthProps {
    private String issuer;
    private String jwtSecret;
    private Duration jwtLifetime;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Duration getJwtLifetime() {
        return jwtLifetime;
    }

    public void setJwtLifetime(Duration jwtLifetime) {
        this.jwtLifetime = jwtLifetime;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
}
