package com.gussoft.bigsignerclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanzaeLog {

    public static void main(String[] args) {
        LanzaeLog lanzaeLog = new LanzaeLog();
        lanzaeLog.crearFileLog();

    }


    public void crearFileLog(){
        LoggerController controller = new LoggerController();
        List<String> err = new ArrayList<>();
        err.add("welcome");
        err.add("sabe");
        try{
            System.out.println(err.get(2).toString());
        }catch (Exception e){

            e.printStackTrace();
            String error = " Clase :: " + Arrays.stream(new Throwable(e).getStackTrace()).findFirst().get().getClassName();
            error = error + " Linea Error :: " + Arrays.stream(new Throwable(e).getStackTrace()).findFirst().get().getLineNumber();
            System.out.println( error);
            controller.info(e.toString(), error);
        }

    }
}
