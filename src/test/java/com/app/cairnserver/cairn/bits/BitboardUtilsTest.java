package com.app.cairnserver.cairn.bits;

import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.bits.positions.StatePositions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitboardUtilsTest {


    @Test
    public void testCountPieces() {
        Assertions.assertEquals(5, BitboardUtils.countTotalPieces(0b011111));
        Assertions.assertEquals(5, BitboardUtils.countTotalPieces(0b01111100000));
        Assertions.assertEquals(3, BitboardUtils.countTotalPieces(0b0111));
    }

    @Test
    public void testAddShaman() {
        Assertions.assertEquals(BitboardPositions.D1, BitboardUtils.computeAddShaman(0, StatePositions.CURRENT_PLAYER | StatePositions.ADD_SHAMAN));
        Assertions.assertEquals(BitboardPositions.B1, BitboardUtils.computeAddShaman(0, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(BitboardPositions.B5, BitboardUtils.computeAddShaman(0, StatePositions.ADD_SHAMAN));
        Assertions.assertEquals(BitboardPositions.D5, BitboardUtils.computeAddShaman(0, 0));

        // Spaces occupied
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, StatePositions.CURRENT_PLAYER | StatePositions.ADD_SHAMAN));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, StatePositions.ADD_SHAMAN));
        Assertions.assertEquals(0, BitboardUtils.computeAddShaman(Integer.MAX_VALUE, 0));
    }

    @Test
    public void testBlueShamanMovesStraight() {
        Assertions.assertEquals(0b000000000000001000101000100000000, BitboardUtils.computeShamanMoves(BitboardPositions.C3,  0, StatePositions.CURRENT_PLAYER | StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b000000000000000000000000001000100, BitboardUtils.computeShamanMoves(BitboardPositions.A1,  0, StatePositions.CURRENT_PLAYER | StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000100010000010000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.A5,  0, StatePositions.CURRENT_PLAYER | StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000010000010001, BitboardUtils.computeShamanMoves( BitboardPositions.E1,  0, StatePositions.CURRENT_PLAYER | StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000101000100000000000000000000, BitboardUtils.computeShamanMoves(BitboardPositions.E5,  0, StatePositions.CURRENT_PLAYER | StatePositions.MOVE_SHAMAN));
    }

    @Test
    public void testBlueShamanMovesDiagonal() {
        Assertions.assertEquals(0b000000000000010100000001010000000, BitboardUtils.computeShamanMoves( BitboardPositions.C3,  0, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(0b000000000000000000000000010000000, BitboardUtils.computeShamanMoves(BitboardPositions.A1,  0, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(0b00001100000000100000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.A5,  0, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(0b000000000000000000000001000000000, BitboardUtils.computeShamanMoves( BitboardPositions.E1,  0, StatePositions.CURRENT_PLAYER));
        Assertions.assertEquals(0b00000100000010000000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.E5,  0, StatePositions.CURRENT_PLAYER));
    }

    @Test
    public void testRedShamanMovesStraight() {
        Assertions.assertEquals(0b000000000000001000101000100000000, BitboardUtils.computeShamanMoves(BitboardPositions.C3, 0, StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000000001000101, BitboardUtils.computeShamanMoves(BitboardPositions.A1, 0, StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000100010000010000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.A5, 0, StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000000000000000000010000010001, BitboardUtils.computeShamanMoves(BitboardPositions.E1, 0, StatePositions.MOVE_SHAMAN));
        Assertions.assertEquals(0b00000001000100000000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.E5, 0, StatePositions.MOVE_SHAMAN));
    }

    @Test
    public void testRedShamanMovesDiagonal() {
        Assertions.assertEquals(0b000000000000010100000001010000000, BitboardUtils.computeShamanMoves(BitboardPositions.C3, 0, 0));
        Assertions.assertEquals(0b00000000000000000000000010000001, BitboardUtils.computeShamanMoves( BitboardPositions.A1, 0, 0));
        Assertions.assertEquals(0b00001000000000100000000000000000, BitboardUtils.computeShamanMoves( BitboardPositions.A5, 0, 0));
        Assertions.assertEquals(0b00000000000000000000001000000001, BitboardUtils.computeShamanMoves( BitboardPositions.E1, 0, 0));
        Assertions.assertEquals(0b00000000000010000000000000000000, BitboardUtils.computeShamanMoves(BitboardPositions.E5, 0, 0));
    }

    @Test
    public void testCheckExits(){
        Assertions.assertTrue(BitboardUtils.checkBlueExit(BitboardPositions.BLUE_EXIT));
        Assertions.assertFalse(BitboardUtils.checkBlueExit(0));

        Assertions.assertTrue(BitboardUtils.checkRedExit(BitboardPositions.RED_EXIT));
        Assertions.assertFalse(BitboardUtils.checkRedExit(0));
    }

    @Test
    public void testClearExits(){
        Assertions.assertEquals(0, BitboardUtils.clearBlueExit(BitboardPositions.BLUE_EXIT));
        Assertions.assertEquals(0, BitboardUtils.clearBlueExit(0));

        Assertions.assertEquals(0, BitboardUtils.clearRedExit(BitboardPositions.RED_EXIT));
        Assertions.assertEquals(0, BitboardUtils.clearRedExit(0));
    }


    @Test
    public void testMovePieces(){
        Assertions.assertEquals(BitboardPositions.C5, BitboardUtils.movePiece(BitboardPositions.A1, BitboardPositions.A1, BitboardPositions.C5));
        Assertions.assertEquals(BitboardPositions.E5, BitboardUtils.movePiece(BitboardPositions.E1, BitboardPositions.E1, BitboardPositions.E5));
        Assertions.assertEquals(BitboardPositions.E1, BitboardUtils.movePiece(BitboardPositions.B5, BitboardPositions.B5, BitboardPositions.E1));
        Assertions.assertEquals(BitboardPositions.A5, BitboardUtils.movePiece(BitboardPositions.A5, BitboardPositions.A5, BitboardPositions.A5));
        Assertions.assertEquals(BitboardPositions.A1, BitboardUtils.movePiece(BitboardPositions.E5, BitboardPositions.E5, BitboardPositions.A1));
    }
}
