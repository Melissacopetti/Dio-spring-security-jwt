package dio.springsecurityjwt.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.List;

import java.util.stream.Collectors;



public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String subject, String issuer, Date expiration, Map<String, Object> claims, String key) {
            JwtBuilder builder = Jwts.builder();

builder.claim("string-claim", "string-value");
builder.claim("number-claim", 42);
builder.claim("bool-claim", true);
builder.claim("datetime-claim", Instant.now());


String token = builder.compact();


        claims.forEach(builder::claim);

        return builder.compact();
    }

    /**
     * @param token
     * @param key
     * @return
     */
    public static JWTObject parse(String token, String key) {
        JWTObject object = new JWTObject();

        io.jsonwebtoken.Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());
        object.setRoles(claims.get(ROLES_AUTHORITIES, new ArrayList<String>()));
        
        return object;
        

        private static List<String> checkRoles(List<String> roles){
            return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    
}
}
}