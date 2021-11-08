package com.app.cairnserver.cairn.ai;

import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;

import java.util.*;
import java.util.stream.Collectors;

public record Node(Board board, int step, boolean blue) {


    public Collection<Node> getNodes() {
        final List<Node> actions = new LinkedList<>();


        final Map<Integer, Integer> allPossibleMoves = BoardUtils.getPossibleMoves(board);

        final Collection<Board> boards = allPossibleMoves.entrySet().stream().flatMap(moves -> {

            final Collection<Board> allBoardMoves = new HashSet<>();
            // Split the moves into individual moves
            for (int j = 1; j < 26; j++) {
                if ((moves.getValue() & 1 << j) != 0) {
                    final Board movedBoard = BoardUtils.movePiece(board, moves.getKey(), moves.getValue() & 1 << j);
                    //  BitboardUtils.printBitboard(movedBoard.bluePieces, movedBoard.redPieces);
                    allBoardMoves.add(movedBoard);
                }
            }

            if (BoardUtils.countPiecesInPlay(board) < 5 && BitboardUtils.computeAddShaman(board.allPieces, board.state) != 0) {
             //   allBoardMoves.add(BoardUtils.addShaman(board));
            }

            return allBoardMoves.stream();
        }).collect(Collectors.toSet());

        boards.forEach(board -> {
            actions.add(new Node(board, step + 1, blue));
        });

        return actions;
    }

    public boolean isTerminal() {
        return board.blueScore == 4 || board.redScore == 4 || step > 9;
    }

    public double getUtility() {

      return BoardUtils.scoreBoard(board, blue);
    }
}