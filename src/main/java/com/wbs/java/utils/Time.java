package com.wbs.java.utils;

public abstract class Time {
    private static int delta;
    private static long lastFrame = 0L;

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static void updateDelta() {
        long time = getTime();
        delta = (int) (time - lastFrame);
        lastFrame = time;
    }

    public static long getLastFrame() {
        return lastFrame;
    }

    public static int getDelta() {
        return delta;
    }

    // Time in seconds it took to complete last frame
    public static float deltaTime() {
        return ((float)getDelta()) / 1000.0f;
    }

}
