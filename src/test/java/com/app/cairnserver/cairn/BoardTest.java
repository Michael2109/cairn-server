package com.app.cairnserver.cairn;

import com.app.cairnserver.cairn.ai.MinMaxTemplate;
import com.app.cairnserver.cairn.ai.State;
import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.bits.StateUtils;
import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testInitialState(){
        final Board board = new Board();
        Assertions.assertTrue(StateUtils.getCurrentPlayer(board.state));
        Assertions.assertTrue(StateUtils.getAddShaman(board.state));
        Assertions.assertTrue(StateUtils.getJumpShaman(board.state));
        Assertions.assertTrue(StateUtils.getMoveShaman(board.state));
        Assertions.assertTrue(StateUtils.getTransformation(board.state));
    }

    @Test
    public void testMinMax() {

        MinMaxTemplate.minimaxDecision(new State(new Board(), 0));
    }


    @Test
    public void testMovePiece() {

        final Board board = new Board();
        final Board updatedBoard = BoardUtils.movePiece(board, BitboardPositions.A1, BitboardPositions.A2);

        Assertions.assertFalse(StateUtils.getCurrentPlayer(updatedBoard.state));
        Assertions.assertFalse(StateUtils.getMoveShaman(updatedBoard.state));
    }
}
