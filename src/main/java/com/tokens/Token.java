package com.tokens;

import io.swagger.v3.core.util.Json;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Token {
    public static String getToken(String email, String password){
        JSONObject jsonObject = new JSONObject();
        String header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        String payload = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        String verifySignature = DigestUtils.sha256Hex(header + "." + payload + "true");
        String res = header + "." + payload + "." + verifySignature;
        return res;
    }
    public static boolean checkToken(String token){
        List<String> tokenList = Arrays.asList(token.split("\\."));
        String verifySignature = DigestUtils.sha256Hex(tokenList.get(0) + "." + tokenList.get(1) + "true");
        return verifySignature.equals(tokenList.get(2));
    }
    public static String getEmail(String token){
        List<String> tokenList = Arrays.asList(token.split("\\."));
        String payload = new String(Base64.getDecoder().decode(tokenList.get(1)));
        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.get("email").toString();
    }
    public static String getPassword(String token){
        List<String> tokenList = Arrays.asList(token.split("\\."));
        String payload = new String(Base64.getDecoder().decode(tokenList.get(1)));
        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.get("password").toString();
    }
}

