package com.wbs.java.utils.position;

import lombok.Getter;
import lombok.Setter;

public class PositionChange<TYPE extends Vector> {

    @Getter @Setter private final TYPE oldPos;
    @Getter @Setter private final TYPE newPos;
    public PositionChange(TYPE oldPos, TYPE newPos) {
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

}
