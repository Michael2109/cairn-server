package com.app.cairnserver.cairn;

import com.app.cairnserver.cairn.board.Board;

public class AIUtils {

    public static int calculateBlueScore(final Board board){
        return board.blueScore;
    }

    public static int calculateRedScore(final Board board){
        return board.redScore;
    }
}
