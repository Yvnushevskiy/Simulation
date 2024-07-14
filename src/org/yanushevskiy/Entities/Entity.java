package org.yanushevskiy.Entities;

import org.yanushevskiy.Actions.Simulationinfo;

public class Entity {
    protected int x, y;


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "E";
    }

}
