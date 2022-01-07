package com.gussoft.textract;

public class DemoText {
    public static void main(String[] args) {
        double dVal = 20.239004;
        double dVal2 = 3051.521540;
        float val = 98.56251f;
        /*
        System.out.println("Double Value: "+dVal);
        String format = "0.00";
        NumberFormat formatter = new DecimalFormat(format);
        String newDVal = formatter.format(dVal);
        System.out.println("Value After Formatting: "+newDVal);
       */
        System.out.println("Double Value: "+dVal);
        System.out.println("Double Value 2 : "+dVal2);
        String con = "96.56254";
        String valor = String.format("S/ %.2f", (double) val);
        String valor2 = String.format("$ %,.2f", dVal2);
        System.out.println("valor = " + valor);
        System.out.println("valor2 = " + valor2);
        System.out.format("Value after Formatting: %.3f", dVal);
    }
}
