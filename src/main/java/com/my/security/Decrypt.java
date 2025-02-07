package com.my.security;

import com.my.secret.Secret;

public class Decrypt {
	
    public static String data(String password) {
        StringBuilder decryptedPwd = new StringBuilder();
        
        for (int i = 0; i < password.length(); i++) {
            decryptedPwd.append((char) (password.charAt(i) - Secret.getKey()));
        }
        
        return decryptedPwd.toString();
    }
}
