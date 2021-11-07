package com.app.cairnserver.cairn.bits;

import com.app.cairnserver.cairn.bits.positions.BitboardPositions;
import com.app.cairnserver.cairn.bits.positions.StatePositions;
import com.app.cairnserver.cairn.board.Board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitboardUtils {

    // Ranks
    public static final int RANK_1 = 0b000000000000000000000111110;
    public static final int RANK_2 = 0b000000000000000011111000000;
    public static final int RANK_3 = 0b000000000001111100000000000;
    public static final int RANK_4 = 0b000000111110000000000000000;
    public static final int RANK_5 = 0b011111000000000000000000000;

    // File
    public static final int FILE_A = 0b010000100001000010000100000;
    public static final int FILE_B = 0b001000010000100001000010000;
    public static final int FILE_C = 0b000100001000010000100001000;
    public static final int FILE_D = 0b000010000100001000010000100;
    public static final int FILE_E = 0b000001000010000100001000010;
    private static final int ALL_POSITIONS = 0b1111111111111111111111111111;
    private static final int[] RANK_MASK = {RANK_1, RANK_2, RANK_3, RANK_4, RANK_5};
    private static final int[] FILE_MASK = {FILE_A, FILE_B, FILE_C, FILE_D, FILE_E};

    public static int computeAddShaman(final int pieces, final int boardState) {

        // Check if there are pieces available to add
        final int piecesInPlay = countTotalPieces(pieces);

        int possibleMove = 0;

        if (piecesInPlay != 5) {
            if((boardState & StatePositions.ADD_SHAMAN) != 0){

                // Add Shaman White
                    if (StateUtils.getCurrentPlayer(boardState)) {
                        if ((pieces & 1 << BitboardPositions.A4) == 0) {
                            possibleMove = 1 << BitboardPositions.D1;
                        }
                    } else {
                        if ((pieces & 1 << BitboardPositions.B5) == 0) {
                            possibleMove = 1 << BitboardPositions.B5;
                        }
                    }

                }
               else {
                   // Add Shaman Black
                    if (StateUtils.getCurrentPlayer(boardState)) {
                        if ((pieces & 1 << BitboardPositions.A2) == 0) {
                            possibleMove = 1 << BitboardPositions.B1;
                        }
                    } else {
                        if ((pieces & 1 << BitboardPositions.D5) == 0) {
                            possibleMove = 1 << BitboardPositions.D5;
                        }
                    }

                }
            }

        return possibleMove;

    }

    public static int computeShamanMoves(final int position, final int allPieces, final int boardState) {

        final int possibleMoves;

        if ((boardState & StatePositions.MOVE_SHAMAN) != 0) {
            possibleMoves = computeMoveStraight(position, allPieces);
        } else {
            possibleMoves = computeMoveDiagonal(position, allPieces);
        }

        final int movesWithExits;

        // Handle exits
        if ((boardState & StatePositions.CURRENT_PLAYER) != 0) {
            if ((position & RANK_5) != 0) {
                movesWithExits = possibleMoves | (BitboardPositions.BLUE_EXIT);
            } else {
                movesWithExits = possibleMoves & ~( BitboardPositions.BLUE_EXIT);
            }
        } else {
            if ((position & RANK_1) != 0) {
                movesWithExits = possibleMoves | ( BitboardPositions.RED_EXIT);
            } else {
                movesWithExits = possibleMoves & ~( BitboardPositions.RED_EXIT);
            }
        }

        return movesWithExits & (~allPieces) & ALL_POSITIONS;
    }

    private static int computeMoveStraight(final int position, final int otherPieces) {
        //   1
        // 4 S 2
        //   3

        final int clipFileE = position & ~FILE_E;
        final int clipFileA = position & ~FILE_A;

        final int spot1 = position >> 5;
        final int spot2 = clipFileE >> 1;

        final int spot3 = position << 5;
        final int spot4 = clipFileA << 1;

        final int moves = spot1 | spot2 | spot3 | spot4;

        return moves & (~otherPieces) & ALL_POSITIONS;
    }


    private static int computeMoveDiagonal(final int position, final int otherPieces) {
        // 1   3
        //   S
        // 7   5

        final int clipFileE = position & ~FILE_E;
        final int clipFileA = position & ~FILE_A;

        final int spot1 = clipFileA >> 4;
        final int spot3 = clipFileE >> 6;
        final int spot5 = clipFileE << 4;
        final int spot7 = clipFileA << 6;


        final int moves = spot1 | spot3 | spot5 | spot7;

        return moves & (~otherPieces) & ALL_POSITIONS;
    }

    public static int movePiece(final int pieces, final int sourcePosition, final int targetPosition){
        return (pieces & ~sourcePosition) | targetPosition;
    }

    public static int countTotalPieces(final int pieces) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((pieces & 1 << i) != 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean checkBlueExit(final int pieces){
        return (pieces & BitboardPositions.BLUE_EXIT) != 0;
    }

    public static boolean checkRedExit(final int pieces){
        return (pieces & BitboardPositions.RED_EXIT) != 0;
    }

    public static int clearBlueExit(final int bluePieces){
        return bluePieces & ~BitboardPositions.BLUE_EXIT;
    }

    public static int clearRedExit(final int redPieces){
        return redPieces & ~BitboardPositions.RED_EXIT;
    }

    private static long[] getAllPositions() {
        long currentPosition = 1L;
        final long[] allPositions = new long[64];
        allPositions[0] = currentPosition;
        for (int i = 1; i < 64; i++) {
            currentPosition = currentPosition << 1;
            allPositions[i] = currentPosition;
        }
        return allPositions;
    }

    private static void findBestMove(final Board board) {

        Arrays.stream(getAllPositions()).forEach(position -> {
            //   final long moves = getValidMoves(board, position);

        });
    }

    public static void printBitboard(final int bluePieces, final int redPieces) {
        final String fullBinaryBlue = String.format("%32s", Integer.toBinaryString(bluePieces)).replace(' ', '0');
        final String fullBinaryRed = String.format("%32s", Integer.toBinaryString(redPieces)).replace(' ', '0');

        final StringBuilder sb1 = new StringBuilder();
        for(int i = 0; i < 32; i++){
            if(fullBinaryRed.charAt(i) != '0'){
                sb1.append('2');
            } else {
                sb1.append(fullBinaryBlue.charAt(i));
            }
        }

        final String fullBinary = sb1.toString();

        final char redExitBit = fullBinary.charAt(5);
        final char blueExitBit = fullBinary.charAt(31);

        final String binary = fullBinary.substring(6).substring(0, 25);

        final List<Character> chars = binary.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        final List<List<Character>> rows = getBatches(chars, 5);

        System.out.println("----------------------------------");
        rows.stream().forEach(row -> {
            final StringBuilder sb = new StringBuilder();
            Collections.reverse(row);
            row.forEach(character -> sb.append(character));

            System.out.println(sb);
        });
        System.out.println("----------------------------------");
        System.out.println();

    }

    public static void printBitboard(final int bitboard) {
        final String fullBinary = String.format("%32s", Integer.toBinaryString(bitboard)).replace(' ', '0');

        final char redExitBit = fullBinary.charAt(5);
        final char blueExitBit = fullBinary.charAt(31);

        final String binary = fullBinary.substring(6).substring(0, 25);

        final List<Character> chars = binary.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        final List<List<Character>> rows = getBatches(chars, 5);

        System.out.println("----------------------------------");
        System.out.println("Board: " + fullBinary);
        System.out.println(redExitBit);
        rows.stream().forEach(row -> {
            final StringBuilder sb = new StringBuilder();
            Collections.reverse(row);
            row.forEach(character -> sb.append(character));

            System.out.println(sb);
        });
        System.out.println(blueExitBit);

        printBitboardBinary(bitboard);
        System.out.println("----------------------------------");
        System.out.println();

    }

    public static void printBitboardBinary(final int bitboard) {
        final String binary = String.format("0b%32s", Integer.toBinaryString(bitboard)).replace(' ', '0');

        System.out.println(binary);
    }

    private static <T> List<List<T>> getBatches(final List<T> collection, final int batchSize) {
        return IntStream.iterate(0, i -> i < collection.size(), i -> i + batchSize)
                .mapToObj(i -> collection.subList(i, Math.min(i + batchSize, collection.size())))
                .collect(Collectors.toList());
    }
}













