package com.example.blood.TestingModels;

import android.graphics.Bitmap;

import java.util.regex.Pattern;

public class DeliveryReqTester {

    public DeliveryReqTester(){}

    public Boolean updateValidator(String name, String area, String contact) {
        if (!name.isEmpty() && !area.isEmpty() && !contact.isEmpty()) {
            if (!Pattern.matches("[a-z A-Z]+", name))
                return false;
            if (name.length() < 3)
                return false;
            if (area.length() < 5)
                return false;
            if (contact.length() == 10) {
                if(contact.length() == 10 && !contact.substring(0, 1).equals("0"))
                    return false;
            } else
                return false;
        } else
            return false;
        return true;
    }

    public Boolean validator(String name, String area, String contact, Bitmap image) {
        if (!name.isEmpty() && !area.isEmpty() && !contact.isEmpty()) {
            if (image == null) return false;

            if (!Pattern.matches("[a-z A-Z]+", name)) return false;

            if (name.length() < 3) return false;

            if (area.length() < 5) return false;

            if (contact.length() == 10) {
                if (contact.length() == 10 && !contact.substring(0, 1).equals("0")) return false;

            } else return false;

        } else return false;
        return true;
    }
}
