package co.baboon.store.auth.services.implementations;

import co.baboon.store.auth.AuthProps;
import co.baboon.store.auth.services.JwtService;
import co.baboon.store.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtServiceImpl implements JwtService {
    private final AuthProps authProps;
    private final String ID_KEY = "id";
    private final Algorithm signingAlgorithm;

    public JwtServiceImpl(AuthProps authProps) {
        this.authProps = authProps;
        
        signingAlgorithm = Algorithm.HMAC256(authProps.getJwtSecret());
    }

    public String generateToken(User user) {
        var currentTimeMillis = System.currentTimeMillis();
        
        return JWT.create()
                .withIssuer(authProps.getIssuer())
                .withClaim(ID_KEY, user.id())
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + authProps.getJwtLifetime().toMillis()))
                .sign(signingAlgorithm);
    }
    
    @Override
    public Long extractUserId(String token) {
        var decodedJwt = decodeJwt(token);
        return decodedJwt.getClaim(ID_KEY).asLong();
    }

    private DecodedJWT decodeJwt(String token) {
        var verifier = JWT.require(signingAlgorithm)
                .withIssuer(authProps.getIssuer())
                .withClaimPresence(ID_KEY)
                .acceptNotBefore(System.currentTimeMillis())
                .build();
        return verifier.verify(token);
    }
}
