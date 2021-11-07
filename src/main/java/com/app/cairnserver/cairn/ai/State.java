package com.app.cairnserver.cairn.ai;

import com.app.cairnserver.cairn.bits.positions.StatePositions;
import com.app.cairnserver.cairn.board.Board;
import com.app.cairnserver.cairn.board.BoardUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record State(Board board) {

    public Collection<State> getActions() {
        List<State> actions = new LinkedList<>();

        final boolean currentPlayer = (board.state & StatePositions.CURRENT_PLAYER) != 0;
        final Map<Integer, Integer> allPossibleMoves = BoardUtils.getPossibleMoves(board, currentPlayer);
        System.out.println("All possible moves");
        System.out.println(allPossibleMoves);
        final Collection<Board> boards = allPossibleMoves.entrySet().stream().map(move -> BoardUtils.movePiece(board, move.getKey(), move.getValue())).collect(Collectors.toSet());

        boards.forEach(board -> {
            actions.add(new State(board));
        });

        return actions;
    }

    public boolean isTerminal() {
        return board.blueScore == 4;
    }

    public double getUtility() {
        if (board.blueScore == 4) {
            return 1;
        } else {
            return -1;
        }
    }

}
