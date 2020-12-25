package com.nirmit.brainvireapp.usage;

public class ValidationHandler {

    public static boolean Validation(String email,String password) {

        if(email.equals("test@android.com") && password.equals("123456")) {
            return true;
        } else {
            return false;
        }
    }

}
