package com.app.cairnserver.model.actions;

public class MovePiece {

    private Position source, target;

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "MovePiece{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
