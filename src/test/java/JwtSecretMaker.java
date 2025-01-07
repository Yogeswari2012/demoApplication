import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;

import static io.jsonwebtoken.security.Keys.secretKeyFor;


public class JwtSecretMaker {

    @Test
    public void generateSecretKey() {
        SecretKey key = secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println(key);
        String secret = Base64.getEncoder().encodeToString(key.getEncoded());
       System.out.println(secret);
    }
}
