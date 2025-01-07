package org.demo.demoapplication.webToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String secretKey = "QOpzb9QUJjytuPocaBvWsc8w20I1rsWl7jzSkLbpEm5Nnhdv1+dGuEy6DmNdH7Fo81btF7DT4TypBWUPGb7MsA==";

    private static long expirationTime = TimeUnit.MINUTES.toMillis(10);

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("name", userDetails.getUsername()+Date.from(Instant.now()));

       return  Jwts.builder()
               .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(getSecretKey())
                .compact();
    }


    private SecretKey getSecretKey() {
       byte[] key=  Base64.getDecoder().decode(secretKey);
       return Keys.hmacShaKeyFor(key);
    }
}
