package com.wbs.java.utils.position;

import com.wbs.simplog.Log;

public class Vector2D extends Vector {
    private static final Log LOG = new Log(Vector2D.class);

    public static final int BYTES = 2 * Float.BYTES;

    public Vector2D (double x, double y) {
        super(2, false, (float) x, (float) y);
    }

    public Vector2D (float x, float y) {
        super(2, false, x, y);
    }

    public Vector2D(int x, int y) {
        super(2, false, x, y);
    }

    public Vector2D copy() {
        return new Vector2D(getX(), getY());
    }

    public float getX() {
        return super.getIndex(0);
    }

    public float getY() {
        return super.getIndex(1);
    }

    public void setX(float newValue) {
        setIndex(0, newValue);
    }

    public void setY(float newValue) {
        setIndex(1, newValue);
    }

    public void set(Vector2D newValue) {
        setX(newValue.getX());
        setY(newValue.getY());
    }

    public void set(float x, float y) {
        setX(x);
        setY(y);
    }

    public void shiftX(float shift) {
        shiftIndex(0, shift);
    }

    public void shiftY(float shift) {
        shiftIndex(1, shift);
    }

    public void shiftXBounded(float shift, float bound) {
        shiftIndexBounded(0, shift, bound);
    }

    public void shiftYBounded(float shift, float bound) {
        shiftIndexBounded(1, shift, bound);
    }

    public static Vector2D getPointOnRay(Vector2D start, Vector2D end, float distanceAway) {
        // r = distanceAway
        // x = r * sinO
        // y = r * cosO

        float hypotenuse = Vector.euclidianDistance(start, end);
        if (hypotenuse == 0) {
            LOG.warn("Not a ray. start: {}, end: {}", start, end);
            return start;
        }

        float dx = end.getX() - start.getX();
        float cosO =  dx / hypotenuse;

        float dy = end.getY() - start.getY();
        float sinO =  dy / hypotenuse;

        return new Vector2D(distanceAway * cosO + start.getX(), distanceAway * sinO + start.getY());
    }

    public static Vector2D getPointInDirection(Vector2D start, float radians, float radius) {
        double cosO =  Math.cos(radians);
        double sinO =  Math.sin(radians);
        return new Vector2D(radius * cosO + start.getX(), radius * sinO + start.getY());
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() +  ")";
    }
}
