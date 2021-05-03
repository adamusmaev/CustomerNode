package com;

import com.auth0.jwt.JWT;
import com.tokens.Token;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication
public class ActuatorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorServiceApplication.class, args);
        //System.out.println(Token.getToken("12", "10002"));
        //System.out.println(Token.checkToken(Token.getToken("12", "12")));
        //System.out.println(Token.getPassword(Token.getToken("12", "12das")));
    }
}
