package com.app.cairnserver.cairn;

import com.app.cairnserver.cairn.board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitboardTest {


    @Test
    public void testCountPieces() {
        Assertions.assertEquals(5, BitboardUtils.countTotalPieces(0b011111));
        Assertions.assertEquals(5, BitboardUtils.countTotalPieces(0b01111100000));
        Assertions.assertEquals(3, BitboardUtils.countTotalPieces(0b0111));
    }

    @Test
    public void testAddShaman() {
        Assertions.assertEquals(1 << BoardPositions.D1, BitboardUtils.computeAddShaman(0, true, StatePosition.CURRENT_PLAYER | StatePosition.ADD_SHAMAN));
        Assertions.assertEquals(1 << BoardPositions.B1, BitboardUtils.computeAddShaman(0, true, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(1 << BoardPositions.B5, BitboardUtils.computeAddShaman(0, false, StatePosition.ADD_SHAMAN));
        Assertions.assertEquals(1 << BoardPositions.D5, BitboardUtils.computeAddShaman(0, false, 0));

        // Spaces occupied
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, true, StatePosition.CURRENT_PLAYER | StatePosition.ADD_SHAMAN));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, true, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, false, StatePosition.ADD_SHAMAN));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, false, 0));
    }

    @Test
    public void testBlueShamanMovesStraight() {
        Assertions.assertEquals(0b000000000000001000101000100000000, BitboardUtils.computeShamanMoves(BoardPositions.C3,  0, StatePosition.CURRENT_PLAYER | StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b000000000000000000000000001000100, BitboardUtils.computeShamanMoves(BoardPositions.A1,  0, StatePosition.CURRENT_PLAYER | StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000100010000010000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.A5,  0, StatePosition.CURRENT_PLAYER | StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000010000010001, BitboardUtils.computeShamanMoves( BoardPositions.E1,  0, StatePosition.CURRENT_PLAYER | StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000101000100000000000000000000, BitboardUtils.computeShamanMoves(BoardPositions.E5,  0, StatePosition.CURRENT_PLAYER | StatePosition.MOVE_SHAMAN));
    }

    @Test
    public void testBlueShamanMovesDiagonal() {
        Assertions.assertEquals(0b000000000000010100000001010000000, BitboardUtils.computeShamanMoves( BoardPositions.C3,  0, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(0b000000000000000000000000010000000, BitboardUtils.computeShamanMoves(BoardPositions.A1,  0, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(0b00001100000000100000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.A5,  0, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(0b000000000000000000000001000000000, BitboardUtils.computeShamanMoves( BoardPositions.E1,  0, StatePosition.CURRENT_PLAYER));
        Assertions.assertEquals(0b00000100000010000000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.E5,  0, StatePosition.CURRENT_PLAYER));
    }

    @Test
    public void testRedShamanMovesStraight() {
        Assertions.assertEquals(0b000000000000001000101000100000000, BitboardUtils.computeShamanMoves(BoardPositions.C3, 0, StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000000001000101, BitboardUtils.computeShamanMoves(BoardPositions.A1, 0, StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000100010000010000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.A5, 0, StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000010000010001, BitboardUtils.computeShamanMoves(BoardPositions.E1, 0, StatePosition.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000001000100000000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.E5, 0, StatePosition.MOVE_SHAMAN));
    }

    @Test
    public void testRedShamanMovesDiagonal() {
        Assertions.assertEquals(0b000000000000010100000001010000000, BitboardUtils.computeShamanMoves(BoardPositions.C3, 0, 0));
        Assertions.assertEquals(0b00000000000000000000000010000001, BitboardUtils.computeShamanMoves( BoardPositions.A1, 0, 0));
        Assertions.assertEquals(0b00001000000000100000000000000000, BitboardUtils.computeShamanMoves( BoardPositions.A5, 0, 0));
        Assertions.assertEquals(0b00000000000000000000001000000001, BitboardUtils.computeShamanMoves( BoardPositions.E1, 0, 0));
        Assertions.assertEquals(0b00000000000010000000000000000000, BitboardUtils.computeShamanMoves(BoardPositions.E5, 0, 0));
    }

    @Test
    public void testChangePlayer(){
        final int nextPlayer = BitboardUtils.changePlayer(0);
        Assertions.assertEquals(StatePosition.CURRENT_PLAYER, nextPlayer & StatePosition.CURRENT_PLAYER);

        final int originalPlayer = BitboardUtils.changePlayer(nextPlayer);
        Assertions.assertEquals(0, originalPlayer);
    }

    @Test
    public void testCheckExits(){
        Assertions.assertTrue(BitboardUtils.checkBlueExit(BoardPositions.BLUE_EXIT));
        Assertions.assertFalse(BitboardUtils.checkBlueExit(0));

        Assertions.assertTrue(BitboardUtils.checkRedExit(BoardPositions.RED_EXIT));
        Assertions.assertFalse(BitboardUtils.checkRedExit(0));
    }

    @Test
    public void testClearExits(){
        Assertions.assertEquals(0, BitboardUtils.clearBlueExit(BoardPositions.BLUE_EXIT));
        Assertions.assertEquals(0, BitboardUtils.clearBlueExit(0));

        Assertions.assertEquals(0, BitboardUtils.clearRedExit(BoardPositions.RED_EXIT));
        Assertions.assertEquals(0, BitboardUtils.clearRedExit(0));
    }


    @Test
    public void testMovePieces(){
        Assertions.assertEquals(BoardPositions.C5, BitboardUtils.movePiece(BoardPositions.A1, BoardPositions.A1, BoardPositions.C5));
        Assertions.assertEquals(BoardPositions.E5, BitboardUtils.movePiece(BoardPositions.E1, BoardPositions.E1, BoardPositions.E5));
        Assertions.assertEquals(BoardPositions.E1, BitboardUtils.movePiece(BoardPositions.B5, BoardPositions.B5, BoardPositions.E1));
        Assertions.assertEquals(BoardPositions.A5, BitboardUtils.movePiece(BoardPositions.A5, BoardPositions.A5, BoardPositions.A5));
        Assertions.assertEquals(BoardPositions.A1, BitboardUtils.movePiece(BoardPositions.E5, BoardPositions.E5, BoardPositions.A1));
    }
}
