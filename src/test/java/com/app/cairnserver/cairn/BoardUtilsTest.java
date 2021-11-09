package com.app.cairnserver.cairn;

import com.app.cairnserver.cairn.ai.MinMaxTemplate;
import com.app.cairnserver.cairn.ai.Node;
import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.bits.StateUtils;
import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.bits.positions.StatePositions;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardUtilsTest {

    private static <T> Collection<T> getValuesFromMultimap(final Map<?, Collection<T>> map) {
        return map.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Start");
        Board nextMove = new Board();
        boolean blue = true;
        while (sc.hasNextLine()) {
            sc.nextLine();
            System.out.println("Turn: " + (blue ? "Blue" : "Red"));
            nextMove = MinMaxTemplate.minimaxDecision(new Node(nextMove, 0, blue)).board();

            System.out.println(blue ? BoardUtils.scoreBoard(nextMove, blue) : -BoardUtils.scoreBoard(nextMove, blue));
            blue = !blue;
            BitboardUtils.printBitboard(nextMove.bluePieces, nextMove.redPieces);
        }

    }

    @Test
    public void testInitialState() {
        final Board board = new Board();
        Assertions.assertTrue(StateUtils.getCurrentPlayer(board.state));
        Assertions.assertTrue(StateUtils.getAddShaman(board.state));
        Assertions.assertTrue(StateUtils.getJumpShaman(board.state));
        Assertions.assertTrue(StateUtils.getMoveShaman(board.state));
        Assertions.assertTrue(StateUtils.getTransformation(board.state));
    }

    @Test
    public void testGetPossibleMoves() {
        Assertions.assertEquals(Stream.of(BitboardPositions.BLUE_EXIT, BitboardPositions.B4).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.A5, 0))));
        Assertions.assertEquals(Stream.of(BitboardPositions.BLUE_EXIT, BitboardPositions.A4, BitboardPositions.C4).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.B5, 0))));
        Assertions.assertEquals(Stream.of(BitboardPositions.BLUE_EXIT, BitboardPositions.B4, BitboardPositions.D4).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.C5, 0))));
        Assertions.assertEquals(Stream.of(BitboardPositions.BLUE_EXIT, BitboardPositions.C4, BitboardPositions.E4).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.D5, 0))));
        Assertions.assertEquals(Stream.of(BitboardPositions.BLUE_EXIT, BitboardPositions.D4).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.E5, 0))));

        Assertions.assertEquals(Stream.of(BitboardPositions.RED_EXIT, BitboardPositions.B2).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(0, 0, BitboardPositions.A1))));
        Assertions.assertEquals(Stream.of(BitboardPositions.RED_EXIT, BitboardPositions.A2, BitboardPositions.C2).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(0, 0, BitboardPositions.B1))));
        Assertions.assertEquals(Stream.of(BitboardPositions.RED_EXIT, BitboardPositions.B2, BitboardPositions.D2).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(0, 0, BitboardPositions.C1))));
        Assertions.assertEquals(Stream.of(BitboardPositions.RED_EXIT, BitboardPositions.C2, BitboardPositions.E2).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(0, 0, BitboardPositions.D1))));
        Assertions.assertEquals(Stream.of(BitboardPositions.RED_EXIT, BitboardPositions.D2).collect(Collectors.toSet()), getValuesFromMultimap(BoardUtils.getPossibleMoves(new Board(0, 0, BitboardPositions.E1))));
    }

    @Test
    public void testMovePiece() {

        final Board board = new Board();
        final Board updatedBoard = BoardUtils.movePiece(board, BitboardPositions.A1, BitboardPositions.A2);

        Assertions.assertFalse(StateUtils.getCurrentPlayer(updatedBoard.state));
        Assertions.assertFalse(StateUtils.getMoveShaman(updatedBoard.state));
    }

    @Test
    public void testMovePieceBlueExit() {

        final Board a5Board = new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.A5, 0);
        final Board a5BoardMoved = BoardUtils.movePiece(a5Board, BitboardPositions.A5, BitboardPositions.BLUE_EXIT);
        final Board b5Board = new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.B5, 0);
        final Board b5BoardMoved = BoardUtils.movePiece(b5Board, BitboardPositions.B5, BitboardPositions.BLUE_EXIT);
        final Board c5Board = new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.C5, 0);
        final Board c5BoardMoved = BoardUtils.movePiece(c5Board, BitboardPositions.C5, BitboardPositions.BLUE_EXIT);
        final Board d5Board = new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.D5, 0);
        final Board d5BoardMoved = BoardUtils.movePiece(d5Board, BitboardPositions.D5, BitboardPositions.BLUE_EXIT);
        final Board e5Board = new Board(StatePositions.CURRENT_PLAYER, BitboardPositions.E5, 0);
        final Board e5BoardMoved = BoardUtils.movePiece(e5Board, BitboardPositions.E5, BitboardPositions.BLUE_EXIT);

        Assertions.assertEquals(0, a5BoardMoved.bluePieces);
        Assertions.assertEquals(1, a5BoardMoved.blueScore);
        Assertions.assertEquals(0, b5BoardMoved.bluePieces);
        Assertions.assertEquals(1, b5BoardMoved.blueScore);
        Assertions.assertEquals(0, c5BoardMoved.bluePieces);
        Assertions.assertEquals(1, c5BoardMoved.blueScore);
        Assertions.assertEquals(0, d5BoardMoved.bluePieces);
        Assertions.assertEquals(1, d5BoardMoved.blueScore);
        Assertions.assertEquals(0, e5BoardMoved.bluePieces);
        Assertions.assertEquals(1, e5BoardMoved.blueScore);
    }

    @Test
    public void testMovePieceRedExit() {

        final Board a1Board = new Board(0, BitboardPositions.A1, 0);
        final Board a1BoardMoved = BoardUtils.movePiece(a1Board, BitboardPositions.A1, BitboardPositions.RED_EXIT);
        final Board b1Board = new Board(0, BitboardPositions.B1, 0);
        final Board b1BoardMoved = BoardUtils.movePiece(b1Board, BitboardPositions.B1, BitboardPositions.RED_EXIT);
        final Board c1Board = new Board(0, BitboardPositions.C1, 0);
        final Board c1BoardMoved = BoardUtils.movePiece(c1Board, BitboardPositions.C1, BitboardPositions.RED_EXIT);
        final Board d1Board = new Board(0, BitboardPositions.D1, 0);
        final Board d1BoardMoved = BoardUtils.movePiece(d1Board, BitboardPositions.D1, BitboardPositions.RED_EXIT);
        final Board e1Board = new Board(0, BitboardPositions.E1, 0);
        final Board e1BoardMoved = BoardUtils.movePiece(e1Board, BitboardPositions.E1, BitboardPositions.RED_EXIT);

        Assertions.assertEquals(0, a1BoardMoved.redPieces);
        Assertions.assertEquals(1, a1BoardMoved.redScore);
        Assertions.assertEquals(0, b1BoardMoved.redPieces);
        Assertions.assertEquals(1, b1BoardMoved.redScore);
        Assertions.assertEquals(0, c1BoardMoved.redPieces);
        Assertions.assertEquals(1, c1BoardMoved.redScore);
        Assertions.assertEquals(0, d1BoardMoved.redPieces);
        Assertions.assertEquals(1, d1BoardMoved.redScore);
        Assertions.assertEquals(0, e1BoardMoved.redPieces);
        Assertions.assertEquals(1, e1BoardMoved.redScore);
    }


    @Test
    public void testScoreBoard() {

        final Board board = new Board();

        Assertions.assertEquals(0, BoardUtils.scoreBoard(board, true));
        Assertions.assertEquals(-0.0, BoardUtils.scoreBoard(board, false));

        final Board updatedBoard = BoardUtils.movePiece(board, BitboardPositions.A1, BitboardPositions.A2);

        Assertions.assertEquals(30, BoardUtils.scoreBoard(updatedBoard, true));
        Assertions.assertEquals(-30, BoardUtils.scoreBoard(updatedBoard, false));
    }

    @Test
    public void testScoreBlueBitboard() {
        Assertions.assertEquals(250, BoardUtils.scoreBlueBitboard(BitboardPositions.C5, 0));
        Assertions.assertEquals(160, BoardUtils.scoreBlueBitboard(BitboardPositions.C4, 0));
        Assertions.assertEquals(90, BoardUtils.scoreBlueBitboard(BitboardPositions.C3, 0));
        Assertions.assertEquals(40, BoardUtils.scoreBlueBitboard(BitboardPositions.C2, 0));
        Assertions.assertEquals(10, BoardUtils.scoreBlueBitboard(BitboardPositions.C1, 0));
        Assertions.assertEquals(-250d, BoardUtils.scoreBlueBitboard(0, BitboardPositions.C1));
        Assertions.assertEquals(-160d, BoardUtils.scoreBlueBitboard(0, BitboardPositions.C2));
        Assertions.assertEquals(-90d, BoardUtils.scoreBlueBitboard(0, BitboardPositions.C3));
        Assertions.assertEquals(-40d, BoardUtils.scoreBlueBitboard(0, BitboardPositions.C4));
        Assertions.assertEquals(-10d, BoardUtils.scoreBlueBitboard(0, BitboardPositions.C5));
        Assertions.assertEquals(0, BoardUtils.scoreBlueBitboard(BitboardPositions.C5, BitboardPositions.C1));
        Assertions.assertEquals(-20d, BoardUtils.scoreBlueBitboard(BitboardPositions.A1 | BitboardPositions.C2 | BitboardPositions.E2, BitboardPositions.A5 | BitboardPositions.C3 | BitboardPositions.E5));

    }
}
