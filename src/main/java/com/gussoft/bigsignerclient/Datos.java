package com.gussoft.bigsignerclient;


public interface Datos<T> {

    boolean insertLog(T obj);

    boolean updateLog(T obj);

    int searchCode(int code);

}
