package com.gussoft.textract;

public class ImportJar {
    public static void main(String[] args) {

        //String file = "C:\\Users\\LENOVO\\Desktop\\link3.png";
        //QrCreate.generateQR(file,"www.youtube.com",300,300);
        String str = "123";
        boolean isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
        System.out.println(isNumeric);
        str = "121xy";
        isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
        System.out.println(isNumeric);
        str = "0x234";
        isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
        System.out.println(isNumeric);
        String strs = "quien sabe el hecho";

        //String sa = str.match("/\/(.*)\/(.*)/");
        System.out.println("strs = " + strs.matches("sabe"));
    }



}
