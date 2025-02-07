package com.my.security;

import com.my.secret.Secret;

public class Encrypt {
	
    public static String data(String password) {
        StringBuilder encryptedPwd = new StringBuilder();
        
        for (int i = 0; i < password.length(); i++) {
        	encryptedPwd.append((char) (password.charAt(i) + Secret.getKey()));
        }
        
        return encryptedPwd.toString();
    }
}
