package com.wbs.java.utils.position;

import com.wbs.simplog.Log;

import java.util.Arrays;

import static java.lang.Math.*;

public class Vector implements Cloneable {
    private static final Log LOG = new Log(Vector.class);

//    // TODO make a concurrent version of this
//    private final Map<Integer, Float> valueMap = new HashMap<>();

    private final float[] values;
    private final int cardinality;

    protected Vector (int cardinality, boolean zeroExtraDimensions, float ... values) {
        if (values.length > cardinality) {
            throw new RuntimeException("Vector had more values than cardinality: " + cardinality + " - " + Arrays.toString(values));
        } else if (values.length < cardinality && !zeroExtraDimensions) {
            throw new RuntimeException("Vector had " + values.length + " values, but expected " + cardinality + " : " + Arrays.toString(values));
        }

        this.values = new float[cardinality];
        this.cardinality = cardinality;

        for (int i = 0; i < cardinality; i++) {
            this.values[i] = i < values.length ? values[i] : 0;
        }
    }

    protected float getIndex(int i) {
        return values[i];
    }

    protected void setIndex(int i, float value) {
        values[i] = value;
    }

    protected void shiftIndex(int i, float shift) {
        values[i] = values[i] + shift;
    }

    protected void shiftIndexBounded(int i, float shift, float bound) {
        values[i] = values[i] + shift;

        if (bound < 0) {
            if (values[i] > bound) {
                LOG.info("hit neg bound", bound);
                values[i] = bound;
            }
        } else {
            if (values[i] < bound) {
                LOG.info("hit pos bound", bound);
                values[i] = bound;
            }
        }
    }

    protected float[] getValues() {
        return values;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }

        if (((Vector) obj).cardinality != this.cardinality) {
            return false;
        }

        for (int i = 0; i < cardinality; i++) {
            if (this.values[i] != ((Vector) obj).values[i]) {
                return false;
            }
        }

        return true;
    }

    public static <TYPE extends Vector> TYPE Lerp(TYPE currentPosition, TYPE targetPosition, float fractionOfJourney) {
        try {
            TYPE clone = (TYPE) currentPosition.clone();
            float[] currentValues = currentPosition.getValues();
            float[] targetValues = targetPosition.getValues();
            for (int i = 0; i < currentValues.length; i++) {
                clone.setIndex(i, Lerp(currentValues[i], targetValues[i], fractionOfJourney));
            }
            return clone;
        } catch (Exception e) {
            throw new RuntimeException("Failed to Lerp from currentPosition: " + currentPosition + " to targetPosition: " + targetPosition, e);
        }
    }

    public static float manhattanDistance(Vector first, Vector second) {
        verifyCardinalityMatches(first, second);

        float distance = 0.0f;
        for (int i = 0; i < first.cardinality; i++) {
            distance += abs(first.values[i] - second.values[i]);
        }

        return distance;
    }

    public static float euclidianDistance(Vector first, Vector second) {
        verifyCardinalityMatches(first, second);

        float distance = 0.0f;
        for (int i = 0; i < first.cardinality; i++) {
            distance += pow(first.values[i] - second.values[i], 2.0);
        }

        return (float) sqrt(distance);
    }

    private static void verifyCardinalityMatches(Vector first, Vector second) {
        if (first.cardinality != second.cardinality) {
            throw new RuntimeException("Cannot calculate distance between two vectors of different cardinality.");
        }
    }

    private static float Lerp(float currentPoint, float targetPoint, float fractionOfJourney) {
        return (currentPoint * (1.0f - fractionOfJourney)) + (targetPoint * fractionOfJourney);
    }
}
