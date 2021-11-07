package com.app.cairnserver.cairn.bits;

import com.app.cairnserver.cairn.bits.positions.StatePositions;

public class StateUtils {

    public static boolean getCurrentPlayer(final int state){
        return (state & StatePositions.CURRENT_PLAYER) != 0;
    }

    public static boolean getAddShaman(final int state){
        return (state & StatePositions.ADD_SHAMAN) != 0;
    }

    public static boolean getJumpShaman(final int state){
        return (state & StatePositions.JUMP_SHAMAN) != 0;
    }

    public static boolean getMoveShaman(final int state){
        return (state & StatePositions.MOVE_SHAMAN) != 0;
    }

    public static boolean getTransformation(final int state){
        return (state & StatePositions.TRANSFORMATION) != 0;
    }

    public static int changePlayer(final int state){
        return state ^ StatePositions.CURRENT_PLAYER;
    }
    public static int changeAddShaman(final int state){
        return state ^ StatePositions.ADD_SHAMAN;
    }
    public static int changeJumpShaman(final int state){
        return state ^ StatePositions.JUMP_SHAMAN;
    }
    public static int changeMoveShaman(final int state){
        return state ^ StatePositions.MOVE_SHAMAN;
    }
    public static int changeTransformation(final int state){
        return state ^ StatePositions.TRANSFORMATION;
    }
}
