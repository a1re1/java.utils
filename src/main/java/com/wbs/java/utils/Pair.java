package com.wbs.java.utils;

public class Pair<X, Y> {
    public  X first;
    public  Y second;
    public Pair(X x, Y y) {
        this.first = x;
        this.second = y;
    }

    @Override
    public String toString() {
        return "{" + first + " : " + second + "}";
    }

    public static <X, Y> Pair<X, Y> of(X first, Y second) {
        return new Pair<>(first, second);
    }
}
