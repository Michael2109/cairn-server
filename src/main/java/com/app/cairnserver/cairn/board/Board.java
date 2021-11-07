package com.app.cairnserver.cairn.board;

import com.app.cairnserver.cairn.BoardPositions;

public class Board {

    public final int blueScore;
    public final int redScore;

    // Stores state such as current Add Shaman type, Jump Shaman, Transformation, Player etc
    public final int state;

    public final int bluePieces;
    public final int redPieces;
    public final int allPieces;

    public Board() {
        this(0, 0, 0, ( BoardPositions.A1) | ( BoardPositions.C1) | ( BoardPositions.E1), ( BoardPositions.A5) | ( BoardPositions.C5) | (BoardPositions.E5));
    }

    public Board(final int state, final int blueScore, final int redScore, final int bluePieces, final int redPieces) {
        this.state = state;
        this.blueScore = blueScore;
        this.redScore = redScore;
        this.bluePieces = bluePieces;
        this.redPieces = redPieces;
        allPieces = bluePieces | redPieces;
    }
}
