package com.nirmit.brainvireapp.usage;

public class ValidationHandler {

    public static boolean emailValidation(String email) {

        if (email.equals("test@android.com")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean passwordCheck(String password){

        if (password.equals("123456")){
            return true;
        }
        else {
            return false;
        }
    }

}
