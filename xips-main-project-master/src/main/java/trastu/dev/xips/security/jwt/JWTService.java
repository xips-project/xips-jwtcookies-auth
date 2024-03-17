package trastu.dev.xips.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

    private static final String SECRET_KEY = "tBTeEle6IfDgxVXwH0s7bp0aPhQpW9Bw/tppsLTyMJ580KlH1g6ZULwpk5270frEhCtBoditMX9TlBhhZSrlSg==";

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(generateKey())
                .compact();
    }

    public String generateNewToken(String token, UserDetails user) {
        Claims claims = getAllClaims(token);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 24);

        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {

        return getClaims(token, Claims::getSubject);


    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){

        return Jwts.parser()
                .verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationToken(String  token){

        return getClaims(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){

        return getExpirationToken(token).before(new Date());
    }

}
