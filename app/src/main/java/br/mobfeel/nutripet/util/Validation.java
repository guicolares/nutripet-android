package br.mobfeel.nutripet.util;

public class Validation {

    public static boolean simpleText(String v){
        String exp = "^[a-zA-ZÁ-è ]{2,20}$";
        return v.matches(exp);
    }

    public static boolean weight(String v){
        String exp = "^[0-9.,]{1,3}$";
        return v.matches(exp);
    }
}
