package com.agreggio.challenge.birras.santander.common.Util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncodeUtil {

    private final static String  salt = "$2a$04$IwmFubEGW8Nw.majttflsO";

    public static String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), salt);
    }

}
