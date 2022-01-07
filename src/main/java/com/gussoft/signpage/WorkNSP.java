package com.gussoft.signpage;

import java.util.ArrayList;
import java.util.List;

public class WorkNSP {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        String x = "2-6";
        String y = "3-5";
        String p = "1-3";
        NumberSignPage numberSignPage = new NumberSignPage();
        if (x.contains("-") && y.contains("-") && p.contains("-")){
            numberSignPage = getNumberSignPageEntry(x,y,p);
            System.out.println("numberSignPage.getLP() = " + numberSignPage.getLp());
            System.out.println("numberSignPage.getLX() = " + numberSignPage.getLx());
            System.out.println("numberSignPage.getLY() = " + numberSignPage.getLy());
        }else{
            numberSignPage.setP(Integer.parseInt(p));
            numberSignPage.setX(Integer.parseInt(x));
            numberSignPage.setY(Integer.parseInt(y));
            System.out.println("numberSignPage.getP() = " + numberSignPage.getP());
            System.out.println("numberSignPage.getX() = " + numberSignPage.getX());
            System.out.println("numberSignPage.getY() = " + numberSignPage.getY());
        }

    }

    private static NumberSignPage getNumberSignPageEntry(String varx, String vary, String varp) {
        NumberSignPage numberSignPage = new NumberSignPage();
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        List<Integer> listP = new ArrayList<>();
        String[] x = varx.split("-");
        String[] y = vary.split("-");
        String[] p = varp.split("-");
        if(!isEmpty(x[0])) {
            for (String nx : x) {
                listX.add(Integer.parseInt(nx));
            }
        }
        if (!isEmpty(y[0])) {
            for (String ny : y) {
                listY.add(Integer.parseInt(ny));
            }
        }
        if (!isEmpty(p[0])) {
            for (String np : p) {
                listP.add(Integer.parseInt(np));
            }
        }
        numberSignPage.setLx(listX);
        numberSignPage.setLy(listY);
        numberSignPage.setLp(listP);
        return numberSignPage;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
