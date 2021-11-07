package com.app.cairnserver.cairn.board;

import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.bits.positions.StatePositions;

public class Board {

    public final int blueScore;
    public final int redScore;

    // Stores state such as current Add Shaman type, Jump Shaman, Transformation, Player etc
    public final int state;

    public final int bluePieces;
    public final int redPieces;
    public final int allPieces;

    public Board() {
        this(StatePositions.CURRENT_PLAYER | StatePositions.ADD_SHAMAN | StatePositions.JUMP_SHAMAN | StatePositions.MOVE_SHAMAN | StatePositions.TRANSFORMATION, 0, 0, (BitboardPositions.A1) | (BitboardPositions.C1) | (BitboardPositions.E1), (BitboardPositions.A5) | (BitboardPositions.C5) | (BitboardPositions.E5));
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
