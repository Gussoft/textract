package com.gussoft.bigsignerclient;

import java.util.List;

public class Metods<T>{

    private final List<T> a;

    public Metods(List<T> a){
        this.a = a;
    }

    public void addLine(T p){
        this.a.add(p);
    }

    public void upLine(int i, T p){
        this.a.set(i, p);
    }

    public int countLine(){
        return this.a.size();
    }

    public T getLine(int i){
        return (T)this.a.get(i);
    }

}
