package com.app.cairnserver.cairn.ai;

import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;

import java.util.*;

public record Node(Board board, int step, boolean blue) {


    public Collection<Node> getNodes() {
        final List<Node> actions = new LinkedList<>();


        final Map<Integer, Collection<Integer>> allPossibleMoves = BoardUtils.getPossibleMoves(board);

        final Collection<Board> allBoardMoves = new HashSet<>();

        allPossibleMoves.entrySet().forEach(moves -> {
            moves.getValue().forEach(target -> {
                final Board movedBoard = BoardUtils.movePiece(board, moves.getKey(), target);

                allBoardMoves.add(movedBoard);
            });
        });

        if (BoardUtils.countPiecesInPlay(board) < 5 && BitboardUtils.computeAddShaman(board.allPieces, board.state) != 0) {
            allBoardMoves.add(BoardUtils.addShaman(board));
        }

        allBoardMoves.forEach(board -> {
            actions.add(new Node(BoardUtils.refreshBoard(board), step + 1, blue));
        });

        return actions;
    }

    public boolean isTerminal() {
        return board.blueScore == 4 || board.redScore == 4 || step > 1;
    }

    public double getUtility() {

        return BoardUtils.scoreBoard(board, blue);
    }
}
