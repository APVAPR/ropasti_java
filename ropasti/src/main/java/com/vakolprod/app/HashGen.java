package com.vakolprod.app;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class HashGen {
    private static String key;
    private static String HMAC;

    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String generateKey() {
        short KEY_LENGHT = 128;
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[KEY_LENGHT / 8];
        random.nextBytes(bytes);
        return Hex.encodeHexString(bytes);
    }

    public static void genKeyandHmac (String compMove) {
        key = generateKey();
        try {
            HMAC = encode(key, compMove);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getKey () {
        return key;
    }
    public static String getHMAC () {
        return HMAC;
    }
}

