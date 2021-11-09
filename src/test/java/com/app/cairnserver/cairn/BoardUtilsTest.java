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

import java.util.Scanner;

public class BoardUtilsTest {

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
    public void testGetPossibleMoves(){
        final Board board = new Board(~StatePositions.CURRENT_PLAYER | ~StatePositions.MOVE_SHAMAN,0, BitboardPositions.A1);

      //  BoardUtils.getPossibleMoves(board).entrySet().forEach(moves -> moves.getValue().forEach(BitboardUtils::printBitboard));

        BoardUtils.getPossibleMoves(board).entrySet().forEach(moves -> moves.getValue().forEach(BitboardUtils::printBitboard));
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Start");
        Board nextMove = new Board();
        boolean blue = true;
        while(sc.hasNextLine()){
           sc.nextLine();
            System.out.println("Turn: " + (blue ? "Blue" : "Red"));
            nextMove = MinMaxTemplate.minimaxDecision(new Node(nextMove, 0, blue)).board();

            System.out.println(blue ? BoardUtils.scoreBoard(nextMove, blue) : -BoardUtils.scoreBoard(nextMove, blue) );
            blue = !blue;
            BitboardUtils.printBitboard(nextMove.bluePieces, nextMove.redPieces);
        }

    }


    @Test
    public void testMovePiece() {

        final Board board = new Board();
        final Board updatedBoard = BoardUtils.movePiece(board, BitboardPositions.A1, BitboardPositions.A2);

        Assertions.assertFalse(StateUtils.getCurrentPlayer(updatedBoard.state));
        Assertions.assertFalse(StateUtils.getMoveShaman(updatedBoard.state));
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
    public void testScoreBlueBitboard(){
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
        Assertions.assertEquals(0, BoardUtils.scoreBlueBitboard(BitboardPositions.C5,  BitboardPositions.C1));
        Assertions.assertEquals(-20d, BoardUtils.scoreBlueBitboard(BitboardPositions.A1 | BitboardPositions.C2 | BitboardPositions.E2,  BitboardPositions.A5 | BitboardPositions.C3 | BitboardPositions.E5));

    }
}
