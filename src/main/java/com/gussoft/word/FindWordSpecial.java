package com.gussoft.word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWordSpecial {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        FindWordSpecial find = new FindWordSpecial();
        String texto = "Certificado de Estudios Bohorquez Cornejo, Zoe marzo 16 2022";
        int x = find.getSpecialCharacterCount(texto);
        System.out.println("Linea caracter especial = " + x);
    }

    public int getSpecialCharacterCount(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return 0;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b)
            System.out.println("There is a special character in my string ");
        else
            System.out.println("There is no special char.");
        return 0;
    }
}
