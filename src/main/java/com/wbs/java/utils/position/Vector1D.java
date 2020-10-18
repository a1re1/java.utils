package com.wbs.java.utils.position;

public class Vector1D extends Vector {
    public Vector1D(float value) {
        super(1, false, value);
    }

    public float get() {
        return super.getIndex(0);
    }

    public void set(float newValue) {
        setIndex(0, newValue);
    }

    public void shift(float shift) {
        shiftIndex(0, shift);
    }
}
