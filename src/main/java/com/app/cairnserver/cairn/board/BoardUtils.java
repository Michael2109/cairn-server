package com.app.cairnserver.cairn.board;

import com.app.cairnserver.cairn.BitboardUtils;
import com.app.cairnserver.cairn.BoardPositions;

import java.util.HashMap;
import java.util.Map;

public class BoardUtils {

    public static Map<Integer, Integer> getPossibleMoves(final Board board, final boolean blue) {
        final Map<Integer, Integer> allPossibleMoves = new HashMap<>();
        for (int i = 1; i < 26; i++) {
            if (blue) {
                System.out.println(board.bluePieces);
                if ((board.bluePieces & 1 << i) != 0) {
                    System.out.println("HERE");
                    final int possibleMoves = BitboardUtils.computeShamanMoves(1 << i, board.allPieces, board.state);
                    allPossibleMoves.put(1 << i, possibleMoves);
                }
            } else {
                if ((board.redPieces & 1 << i) != 0) {
                    final int possibleMoves = BitboardUtils.computeShamanMoves(1 << i, board.allPieces, board.state);
                    allPossibleMoves.put(1 << i, possibleMoves);
                }
            }
        }
        return allPossibleMoves;
    }

    public static Board movePiece(final Board board, final int startPosition, final int endPosition)  {

        if ((BitboardUtils.computeShamanMoves(startPosition, board.allPieces, board.state) & endPosition) != 0) {
            // Blue piece

            int bluePieces = (board.bluePieces & ~startPosition) | endPosition;

            int blueScore = board.blueScore;
            if ((bluePieces & (1 << BoardPositions.BLUE_EXIT)) != 0) {
                bluePieces = bluePieces & ~(1 << BoardPositions.BLUE_EXIT);
                blueScore++;
            }

            return new Board(board.state, blueScore, board.redScore, bluePieces, board.redPieces);

        } else {
            throw new RuntimeException("Move not allowed!");
        }
    }
}
