package com.app.cairnserver.cairn.board;

import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.bits.StateUtils;
import com.app.cairnserver.cairn.bits.positions.BitboardPositions;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardUtils {

    private static final int SCORE_MULTIPLIER = 200;
    private static final int RANK_MULTIPLIER = 10;

    public static Map<Integer, Integer> getPossibleMoves(final Board board) {
        final Map<Integer, Integer> allPossibleMoves = new LinkedHashMap<>();
        for (int i = 1; i < 26; i++) {
            if (StateUtils.getCurrentPlayer(board.state)) {
                if ((board.bluePieces & 1 << i) != 0) {
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

    public static int countPiecesInPlay(final Board board) {
        return StateUtils.getCurrentPlayer(board.state) ? board.bluePiecesInPlay : board.redPiecesInPlay;
    }

    public static Board addShaman(final Board board) {

        int bluePieces = board.bluePieces;
        int redPieces = board.redPieces;
        int bluePiecesInPlay = board.bluePiecesInPlay;
        int redPiecesInPlay = board.redPiecesInPlay;
        if (StateUtils.getCurrentPlayer(board.state)) {
            final int addShamanMove = BitboardUtils.computeAddShaman(bluePieces, board.state);
            bluePieces = bluePieces | addShamanMove;
            bluePiecesInPlay++;
        } else {
            final int addShamanMove = BitboardUtils.computeAddShaman(redPieces, board.state);
            redPieces = redPieces | addShamanMove;
            redPiecesInPlay++;
        }

        return new Board(StateUtils.changeAddShaman(StateUtils.changePlayer(board.state)), board.blueScore, board.redScore, bluePieces, redPieces, bluePiecesInPlay, redPiecesInPlay);
    }

    public static Board movePiece(final Board board, final int startPosition, final int endPosition) {

        if ((BitboardUtils.computeShamanMoves(startPosition, board.allPieces, board.state) & endPosition) != 0) {

            int bluePieces;
            int redPieces;
            int blueScore = board.blueScore;
            int redScore = board.redScore;

            // Blue
            if (StateUtils.getCurrentPlayer(board.state)) {

                bluePieces = (board.bluePieces & ~startPosition) | endPosition;
                redPieces = board.redPieces;

                if ((bluePieces & BitboardPositions.BLUE_EXIT) != 0) {
                    bluePieces = bluePieces & ~BitboardPositions.BLUE_EXIT;
                    blueScore++;
                }
            } else {
                // Red
                bluePieces = board.bluePieces;
                redPieces = (board.redPieces & ~startPosition) | endPosition;

                if ((redPieces & BitboardPositions.RED_EXIT) != 0) {
                    redPieces = redPieces & ~BitboardPositions.RED_EXIT;
                    redScore++;
                }
            }

            return new Board(StateUtils.changeMoveShaman(StateUtils.changePlayer(board.state)), blueScore, redScore, bluePieces, redPieces, board.bluePiecesInPlay, board.redPiecesInPlay);


        } else {
            throw new RuntimeException("Move not allowed!");
        }
    }

    public static double scoreBlueBitboard(final int bluePieces, final int redPieces) {
        double score = 0;
        // Add score based on how far moved forward
        for (int i = 0; i < 5; i++) {
            final int bitsInRankBlue = Integer.bitCount(bluePieces & BitboardUtils.RANK_MASK[i]);
            final int bitsInRankRed = Integer.bitCount(redPieces & BitboardUtils.RANK_MASK[4 - i]);

            score += (i + 1) * (i + 1) * RANK_MULTIPLIER * bitsInRankBlue;
            score -= (i + 1) * (i + 1) * RANK_MULTIPLIER * bitsInRankRed;
        }
        return score;
    }

    public static double scoreBoard(final Board board, final boolean blue) {
        double score = board.blueScore * SCORE_MULTIPLIER - board.redScore * SCORE_MULTIPLIER;
        score += scoreBlueBitboard(board.bluePieces, board.redPieces);
        if (!blue) {
            score *= -1;
        }
        return score;
    }
}
