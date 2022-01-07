package com.gussoft.signpage;

import java.util.List;

public class NumberSignPage {
    private List<Integer> lx;
    private List<Integer> ly;
    private List<Integer> lp;
    private int x;
    private int y;
    private int p;

    public NumberSignPage() {
    }

    public NumberSignPage(List<Integer> x, List<Integer> y, List<Integer> p) {
        this.lx = x;
        this.ly = y;
        this.lp = p;
    }

    public List<Integer> getLx() {
        return lx;
    }

    public void setLx(List<Integer> lx) {
        this.lx = lx;
    }

    public List<Integer> getLy() {
        return ly;
    }

    public void setLy(List<Integer> ly) {
        this.ly = ly;
    }

    public List<Integer> getLp() {
        return lp;
    }

    public void setLp(List<Integer> lp) {
        this.lp = lp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
