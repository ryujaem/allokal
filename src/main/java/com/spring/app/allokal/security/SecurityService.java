package com.spring.app.allokal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class SecurityService {
    private final String SECRET_KEY = "asdfasdfasfaefawefadsfasdf";
    public String createToken(String Id, long expTime){
        if(expTime<=0){
            throw new RuntimeException("토큰이 만료되었습니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes,signatureAlgorithm.getJcaName());
        log.info("SignatureAlgorithm : "+signingKey);
        return Jwts.builder()
                .setSubject(Id)
                .signWith(signatureAlgorithm,signingKey)
                .setExpiration(new Date(System.currentTimeMillis()+expTime))
                .compact();
    }


    /**
     * 토큰 유효여부 확인
     */
    public Boolean isValidToken(String token) {
        log.info("isValidToken token = {}", token);
        if(token != null) {
            String[] chunks = token.split(".");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            log.info("isValidToken header : ", decoder.decode(chunks[0]));
            log.info("isValidToken payload : ", decoder.decode(chunks[1]));
            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));
            log.info("isValidToken SECRETKEY : ", SECRET_KEY);

            return payload==SECRET_KEY;
        }else{
            return false;
        }

    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token) {
        log.info("getAllClaims token = {}", token);
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 username 가져오기
     */
    public String getUsernameFromToken(String token) {
        String username = String.valueOf(getAllClaims(token).get("username"));
        log.info("getUsernameFormToken subject = {}", username);
        return username;
    }

    /**
     * 토큰 만료기한 가져오기
     */
    public Date getExpirationDate(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * 토큰이 만료되었는지
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}
