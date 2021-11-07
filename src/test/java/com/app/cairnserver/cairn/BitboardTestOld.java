package com.app.cairnserver.cairn;

import org.junit.jupiter.api.Test;

public class BitboardTestOld {


    @Test
    public void testInitialBoardValues() {
       /* final long bitboard = new Board().getBitboard();

        Assertions.assertEquals(0b1111111111111111000000000000000000000000000000001111111111111111L, bitboard);*/
    }

    @Test
    public void testClearRank() {
     /*   final long bitboard = new Board().getBitboard();

        Assertions.assertEquals(0b1111111111111111000000000000000000000000000000001111111100000000L, bitboard & BitboardUtils.createRowClear(0));

        Assertions.assertEquals(0b1111111111111111000000000000000000000000000000000000000011111111L, bitboard & BitboardUtils.createRowClear(1));

        Assertions.assertEquals(0b0000000011111111000000000000000000000000000000001111111111111111L, bitboard & BitboardUtils.createRowClear(7));*/
    }

    @Test
    public void testMaskRank() {
      /*  final long bitboard = new Board().getBitboard();

        Assertions.assertEquals(0b0000000000000000000000000000000000000000000000000000000011111111L, bitboard & BitboardUtils.createRowMask(0));
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000000001111111100000000L, bitboard & BitboardUtils.createRowMask(1));
        Assertions.assertEquals(0b1111111100000000000000000000000000000000000000000000000000000000L, bitboard & BitboardUtils.createRowMask(7));*/
    }

    @Test
    public void testClearFile() {
      /*  final long bitboard = new Board().getBitboard();

        Assertions.assertEquals(0b1111111011111110000000000000000000000000000000001111111011111110L, bitboard & BitboardUtils.createColumnClear(0));
        Assertions.assertEquals(0b1111110111111101000000000000000000000000000000001111110111111101L, bitboard & BitboardUtils.createColumnClear(1));
        Assertions.assertEquals(0b0111111101111111000000000000000000000000000000000111111101111111L, bitboard & BitboardUtils.createColumnClear(7));*/
    }

    @Test
    public void testMaskFile() {
        /*final long bitboard = new Board().getBitboard();

        Assertions.assertEquals(0b0000000100000001000000000000000000000000000000000000000100000001L, bitboard & BitboardUtils.createColumnMask(0));
        Assertions.assertEquals(0b0000001000000010000000000000000000000000000000000000001000000010L, bitboard & BitboardUtils.createColumnMask(1));
        Assertions.assertEquals(0b1000000010000000000000000000000000000000000000001000000010000000L, bitboard & BitboardUtils.createColumnMask(7));*/
    }

    @Test
    public void testKingMoves() {
/*

        Assertions.assertEquals(0b0000000000000000000000000000000000011100000101000001110000000000L, BitboardUtils.computeKingMoves(1L << Positions.D3, 0));
        Assertions.assertEquals(0b0000000000000000000000000000000000000011000000100000001100000000L, BitboardUtils.computeKingMoves(1L << Positions.A3, 0));
        Assertions.assertEquals(0b0000000000000000000000000000000011000000010000001100000000000000L, BitboardUtils.computeKingMoves(1L << Positions.H3, 0));
        Assertions.assertEquals(0b0000101000001110000000000000000000000000000000000000000000000000L, BitboardUtils.computeKingMoves(1L << Positions.C8, 0));
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000000000000111000001010L, BitboardUtils.computeKingMoves(1L << Positions.C1, 0));
*/

        // Add tests to check wont allow moves on own pieces
    }

    @Test
    public void testKnightMoves() {
/*
        Assertions.assertEquals(0b0000000000000000000000000001010000100010000000000010001000010100L, BitboardUtils.computeKnightMoves(1L << Positions.D3, 0));
        Assertions.assertEquals(0b0000000000000000000000000000001000000100000000000000010000000010L, BitboardUtils.computeKnightMoves(1L << Positions.A3, 0));
        Assertions.assertEquals(0b0000000000000000000000000100000000100000000000000010000001000000L, BitboardUtils.computeKnightMoves(1L << Positions.H3, 0));
        Assertions.assertEquals(0b0000000000010001000010100000000000000000000000000000000000000000L, BitboardUtils.computeKnightMoves(1L << Positions.C8, 0));
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000010100001000100000000L, BitboardUtils.computeKnightMoves(1L << Positions.C1, 0));

        // Add tests to check wont allow moves on own pieces*/
    }

    @Test
    public void testWhitePawnMoves() {
/*
        // Forwards
        Assertions.assertEquals(1L << Positions.D4, BitboardUtils.computeWhitePawnMoves(1L << Positions.D3, 0, 0));
        Assertions.assertEquals(0, BitboardUtils.computeWhitePawnMoves(1L << Positions.D8, 0, 0));

        // Blocked
        Assertions.assertEquals(0, BitboardUtils.computeWhitePawnMoves(1L << Positions.D3, 1L << Positions.D4, 0));

        // Attack left
        Assertions.assertEquals(0b0000000000000000000000000000000000001100000000000000000000000000L, BitboardUtils.computeWhitePawnMoves(1L << Positions.D3, 0, 1L << Positions.C4));

        // Attack right
        Assertions.assertEquals(0b0000000000000000000000000000000000011000000000000000000000000000L, BitboardUtils.computeWhitePawnMoves(1L << Positions.D3, 0, 1L << Positions.E4));

        // Attack both
        Assertions.assertEquals(0b0000000000000000000000000000000000011100000000000000000000000000L, BitboardUtils.computeWhitePawnMoves(1L << Positions.D3, 0, 1L << Positions.C4 | 1L << Positions.E4));
*/
    }

    @Test
    public void testBlackPawnMoves() {
/*
        // Forwards
        Assertions.assertEquals(1L << Positions.D2, BitboardUtils.computeBlackPawnMoves(1L << Positions.D3, 0, 0));
        Assertions.assertEquals(0, BitboardUtils.computeBlackPawnMoves(1L << Positions.D1, 0, 0));

        // Blocked
        Assertions.assertEquals(0, BitboardUtils.computeBlackPawnMoves(1L << Positions.D3, 1L << Positions.D2, 0));

        // Attack left
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000011000000000000000000L, BitboardUtils.computeBlackPawnMoves(1L << Positions.C4, 0, 1L << Positions.D3));

        // Attack right
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000110000000000000000000L, BitboardUtils.computeBlackPawnMoves(1L << Positions.D4, 0, 1L << Positions.E3));

        // Attack both
        Assertions.assertEquals(0b0000000000000000000000000000000000000000000111000000000000000000L, BitboardUtils.computeBlackPawnMoves(1L << Positions.D4, 0, 1L << Positions.C3 | 1L << Positions.E3));
*/
    }

    @Test
    public void testRookMoves() {
/*
        Assertions.assertEquals(0b0000100000001000000010000000100000001000111101110000100000001000L, BitboardUtils.computeRookMoves(1L << Positions.D3, 0, 0));
        Assertions.assertEquals(0b0000000100000001000000010000000100000001000000010000000111111110L, BitboardUtils.computeRookMoves(1L << Positions.A1, 0, 0));
        Assertions.assertEquals(0b1111111000000001000000010000000100000001000000010000000100000001L, BitboardUtils.computeRookMoves(1L << Positions.A8, 0, 0));
        Assertions.assertEquals(0b0111111110000000100000001000000010000000100000001000000010000000L, BitboardUtils.computeRookMoves(1L << Positions.H8, 0, 0));
        Assertions.assertEquals(0b1000000010000000100000001000000010000000100000001000000001111111L, BitboardUtils.computeRookMoves(1L << Positions.H1, 0, 0));*/

    }

    @Test
    public void testBishopMoves() {
/*
        Assertions.assertEquals(0b0000100000001000000010000000100000001000111101110000100000001000L, BitboardUtils.computeBishopMoves(1L << Positions.D6, 0, 0));
        Assertions.assertEquals(0b0000100000001000000010000000100000001000111101110000100000001000L, BitboardUtils.computeBishopMoves(1L << Positions.D3, 0, 0));
        Assertions.assertEquals(0b0000000100000001000000010000000100000001000000010000000111111110L, BitboardUtils.computeBishopMoves(1L << Positions.A1, 0, 0));
        Assertions.assertEquals(0b1111111000000001000000010000000100000001000000010000000100000001L, BitboardUtils.computeBishopMoves(1L << Positions.A8, 0, 0));
        Assertions.assertEquals(0b0111111110000000100000001000000010000000100000001000000010000000L, BitboardUtils.computeBishopMoves(1L << Positions.H8, 0, 0));
        Assertions.assertEquals(0b1000000010000000100000001000000010000000100000001000000001111111L, BitboardUtils.computeBishopMoves(1L << Positions.H1, 0, 0));*/

    }
}
