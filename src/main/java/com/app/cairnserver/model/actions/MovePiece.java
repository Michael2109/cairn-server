package com.app.cairnserver.model.actions;

public class MovePiece {

    private int source, target;

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "MovePiece{" +
                "source=" + Integer.toBinaryString(source) +
                ", target=" + Integer.toBinaryString(target) +
                '}';
    }
}
