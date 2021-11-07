package com.app.cairnserver.cairn.ai;

import com.app.cairnserver.cairn.bits.BitboardUtils;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;

import java.util.*;
import java.util.stream.Collectors;

public record State(Board board, int step) {

    public Collection<State> getActions() {
        final List<State> actions = new LinkedList<>();

        final Map<Integer, Integer> allPossibleMoves = BoardUtils.getPossibleMoves(board);


        System.out.println("All possible moves");
        System.out.println(allPossibleMoves);

        final Collection<Board> boards = allPossibleMoves.entrySet().stream().flatMap(moves -> {

            final Collection<Board> allBoardMoves = new HashSet<>();
            // Split the moves into individual moves
            for (int j = 1; j < 26; j++) {
                if ((moves.getValue() & 1 << j) != 0) {
                    final Board movedBoard = BoardUtils.movePiece(board, moves.getKey(), moves.getValue() & 1 << j);
                    System.out.println("Printing blue and red pieces");
                    System.out.println("Score: " + movedBoard.blueScore + " : " + movedBoard.redScore);
                    System.out.println("In Play: " + movedBoard.bluePiecesInPlay + " : " + movedBoard.redPiecesInPlay);
                    BitboardUtils.printBitboard(movedBoard.bluePieces, movedBoard.redPieces);
                    allBoardMoves.add(movedBoard);
                }
            }

            if (BoardUtils.countPiecesInPlay(board) < 5) {
                System.out.println("Adding shaman");
                allBoardMoves.add(BoardUtils.addShaman(board));
            }

            System.out.println("All Possible Moves END");
            return allBoardMoves.stream();
        }).collect(Collectors.toSet());

        boards.forEach(board -> {
            actions.add(new State(board, step+1));
        });

        return actions;
    }

    public boolean isTerminal() {
        return board.blueScore == 4 || board.redScore == 4 || step == 5;
    }

    public double getUtility() {
      return board.blueScore - board.redScore;
    }

}
