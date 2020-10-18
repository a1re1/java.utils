package com.wbs.java.utils.position;

public class Vector3D extends Vector {
    public static final int BYTES = 3 * Float.BYTES;

    public Vector3D (float x, float y, float z) {
        super(3, false, x, y, z);
    }

    private Vector3D (int x, int y, int z) {
        super(3, false, x, y, z);
    }

    public float getX() {
        return super.getIndex(0);
    }

    public float getY() {
        return super.getIndex(1);
    }

    public float getZ() {
        return super.getIndex(2);
    }

    public void setX(float newX) {
        super.setIndex(0, newX);
    }

    public void setY(float newY) {
        super.setIndex(1, newY);
    }

    public void setZ(float newZ) {
        super.setIndex(2, newZ);
    }

    public void set(float x, float y, float z) {
        super.setIndex(0, x);
        super.setIndex(1, y);
        super.setIndex(2, z);
    }
}
