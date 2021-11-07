package com.app.cairnserver.cairn;

public class BitboardUtilsOld {
/*
    // Ranks
    private static final long RANK_1 = 0b00000000000000000000011111;
    private static final long RANK_2 = 0b00000000000000011111000000;
    private static final long RANK_3 = 0b00000000001111100000000000;
    private static final long RANK_4 = 0b00000111110000000000000000;
    private static final long RANK_5 = 0b11111000000000000000000000;

    // File
    private static final long FILE_A = 0b01000010000100001000010000;
    private static final long FILE_B = 0b00100001000010000100001000;
    private static final long FILE_C = 0b00010000100001000010000100;
    private static final long FILE_D = 0b00001000010000100001000010;
    private static final long FILE_E = 0b00000100001000010000100001;

    // Diagonals
    private static final long DIAGONAL_0 = 0b0000000100000000000000000000000000000000000000000000000000000000L;
    private static final long DIAGONAL_1 = 0b0000001000000001000000000000000000000000000000000000000000000000L;
    private static final long DIAGONAL_2 = 0b0000010000000010000000010000000000000000000000000000000000000000L;
    private static final long DIAGONAL_3 = 0b0000100000000100000000100000000100000000000000000000000000000000L;
    private static final long DIAGONAL_4 = 0b0001000000001000000001000000001000000001000000000000000000000000L;
    private static final long DIAGONAL_5 = 0b0010000000010000000010000000010000000010000000010000000000000000L;
    private static final long DIAGONAL_6 = 0b0100000000100000000100000000100000000100000000100000000100000000L;
    private static final long DIAGONAL_7 = 0b1000000001000000001000000001000000001000000001000000001000000001L;
    private static final long DIAGONAL_8 = 0b0000000010000000010000000010000000010000000010000000010000000010L;
    private static final long DIAGONAL_9 = 0b0000000000000000100000000100000000100000000100000000100000000100L;
    private static final long DIAGONAL_10 = 0b0000000000000000000000001000000001000000001000000001000000001000L;
    private static final long DIAGONAL_11 = 0b0000000000000000000000000000000010000000010000000010000000010000L;
    private static final long DIAGONAL_12 = 0b0000000000000000000000000000000000000000100000000100000000100000L;
    private static final long DIAGONAL_13 = 0b0000000000000000000000000000000000000000000000001000000001000000L;
    private static final long DIAGONAL_14 = 0b0000000000000000000000000000000000000000000000000000000010000000L;

    // Anti-diagonal
    private static final long ANTI_DIAGONAL_0 = 0b1000000000000000000000000000000000000000000000000000000000000000L;
    private static final long ANTI_DIAGONAL_1 = 0b0100000010000000000000000000000000000000000000000000000000000000L;
    private static final long ANTI_DIAGONAL_2 = 0b0010000001000000100000000000000000000000000000000000000000000000L;
    private static final long ANTI_DIAGONAL_3 = 0b0001000000100000010000001000000000000000000000000000000000000000L;
    private static final long ANTI_DIAGONAL_4 = 0b0000100000010000001000000100000010000000000000000000000000000000L;
    private static final long ANTI_DIAGONAL_5 = 0b0000010000001000000100000010000001000000100000000000000000000000L;
    private static final long ANTI_DIAGONAL_6 = 0b0000001000000100000010000001000000100000010000001000000000000000L;
    private static final long ANTI_DIAGONAL_7 = 0b0000000100000010000001000000100000010000001000000100000010000000L;
    private static final long ANTI_DIAGONAL_8 = 0b0000000000000001000000100000010000001000000100000010000001000000L;
    private static final long ANTI_DIAGONAL_9 = 0b0000000000000000000000010000001000000100000010000001000000100000L;
    private static final long ANTI_DIAGONAL_10 = 0b0000000000000000000000000000000100000010000001000000100000010000L;
    private static final long ANTI_DIAGONAL_11 = 0b0000000000000000000000000000000000000001000000100000010000001000L;
    private static final long ANTI_DIAGONAL_12 = 0b0000000000000000000000000000000000000000000000010000001000000100L;
    private static final long ANTI_DIAGONAL_13 = 0b0000000000000000000000000000000000000000000000000000000100000010L;
    private static final long ANTI_DIAGONAL_14 = 0b0000000000000000000000000000000000000000000000000000000000000001L;

    // Masks
    private static final long[] RANK_MASK = {RANK_1, RANK_2, RANK_3, RANK_4, RANK_5};
    private static final long[] FILE_MASK = {FILE_A, FILE_B, FILE_C, FILE_D, FILE_E};
    private static final long[] DIAGONAL_MASK = {DIAGONAL_14, DIAGONAL_13, DIAGONAL_12, DIAGONAL_11, DIAGONAL_10, DIAGONAL_9, DIAGONAL_8, DIAGONAL_7, DIAGONAL_6, DIAGONAL_5, DIAGONAL_4, DIAGONAL_3, DIAGONAL_2, DIAGONAL_1, DIAGONAL_0};
    private static final long[] ANTI_DIAGONAL_MASK = {ANTI_DIAGONAL_14, ANTI_DIAGONAL_13, ANTI_DIAGONAL_12, ANTI_DIAGONAL_11, ANTI_DIAGONAL_10, ANTI_DIAGONAL_9, ANTI_DIAGONAL_8, ANTI_DIAGONAL_7, ANTI_DIAGONAL_6, ANTI_DIAGONAL_5, ANTI_DIAGONAL_4, ANTI_DIAGONAL_3, ANTI_DIAGONAL_2, ANTI_DIAGONAL_1, ANTI_DIAGONAL_0};

    // TODO Change to be multiple constants
    public static long createRowClear(final long row) {
        return ~(0b11111111L << (row * 8));
    }

    public static long createRowMask(final long row) {
        return 0b11111111L << (row * 8);
    }

    public static long createColumnClear(final long column) {
        return ~(0b0000000100000001000000010000000100000001000000010000000100000001L << column);
    }

    public static long createColumnMask(final long column) {
        return ~createColumnClear(column);
    }

    public static void printBitboard(final long bitboard) {
        final String binary = String.format("%64s", Long.toBinaryString(bitboard)).replace(' ', '0');

        final List<Character> chars = binary.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        final List<List<Character>> rows = getBatches(chars, 8);

        rows.stream().forEach(row -> {
            final StringBuilder sb = new StringBuilder();
            Collections.reverse(row);
            row.forEach(character -> sb.append(character));

            System.out.println(sb);
        });

        printBitboardBinary(bitboard);
    }


    public static void printBitboardBinary(final long bitboard) {
        final String binary = String.format("0b%64sL", Long.toBinaryString(bitboard)).replace(' ', '0');

        System.out.println(binary);
    }


    public static long getValidMoves(final Board board, final long position) {


        return -1;
    }

    private static int calculateScore(final long pieces){
       // Arrays.stream(getAllPositions()).map(position -> pieces)
        return -1;
    }


    public static long computeKingMoves(final long kingPosition, final long ownPieces) {
        // 1 2 3
        // 8 K 4
        // 7 6 5

        final long kingClipColumnH = kingPosition & ~FILE_H;
        final long kingClipColumnA = kingPosition & ~FILE_A;

        final long spot1 = kingClipColumnA >> 7;
        final long spot2 = kingPosition >> 8;
        final long spot3 = kingClipColumnH >> 9;
        final long spot4 = kingClipColumnH >> 1;

        final long spot5 = kingClipColumnH << 7;
        final long spot6 = kingPosition << 8;
        final long spot7 = kingClipColumnA << 9;
        final long spot8 = kingClipColumnA << 1;

        final long kingMoves = spot1 | spot2 | spot3 | spot4 | spot5 | spot6 | spot7 | spot8;

        return kingMoves & (~ownPieces);
    }

    public static long computeKnightMoves(final long knightPosition, final long ownPieces) {
        //   2  3
        // 1      4
        //     K
        // 8      5
        //   7  6

        final long spot_1_clip = BitboardUtils.createColumnClear(0) & BitboardUtils.createColumnClear(1);
        final long spot_2_clip = BitboardUtils.createColumnClear(0);
        final long spot_3_clip = BitboardUtils.createColumnClear(7);
        final long spot_4_clip = BitboardUtils.createColumnClear(7) & BitboardUtils.createColumnClear(6);

        final long spot_5_clip = BitboardUtils.createColumnClear(7) & BitboardUtils.createColumnClear(6);
        final long spot_6_clip = BitboardUtils.createColumnClear(7);
        final long spot_7_clip = BitboardUtils.createColumnClear(0);
        final long spot_8_clip = BitboardUtils.createColumnClear(0) & BitboardUtils.createColumnClear(1);

        *//* The clipping masks we just created will be used to ensure that no
		under or overflow positions are computed when calculating the
		possible moves of the knight in certain files. *//*

        final long spot_1 = (knightPosition & spot_1_clip) << 6;
        final long spot_2 = (knightPosition & spot_2_clip) << 15;
        final long spot_3 = (knightPosition & spot_3_clip) << 17;
        final long spot_4 = (knightPosition & spot_4_clip) << 10;

        final long spot_5 = (knightPosition & spot_5_clip) >> 6;
        final long spot_6 = (knightPosition & spot_6_clip) >> 15;
        final long spot_7 = (knightPosition & spot_7_clip) >> 17;
        final long spot_8 = (knightPosition & spot_8_clip) >> 10;

        final long KnightValid = spot_1 | spot_2 | spot_3 | spot_4 | spot_5 | spot_6 |
                spot_7 | spot_8;

        return KnightValid & ~ownPieces;
    }

    public static long computeWhitePawnMoves(final long pawnPosition, final long allPieces, final long blackPieces) {

        *//* check the single space infront of the white pawn *//*
        final long white_pawn_one_step = (pawnPosition << 8) & ~allPieces;

	    *//* for all moves that came from rank 2 (home row) and passed the above
		filter, thereby being on rank 3, check and see if I can move forward
		one more *//*
        final long white_pawn_two_steps =
                ((white_pawn_one_step & BitboardUtils.createRowMask(2)) << 8) & ~allPieces;

	    *//* the union of the movements dictate the possible moves forward
		available *//*
        final long white_pawn_valid_moves = white_pawn_one_step | white_pawn_two_steps;

        *//* next we calculate the pawn attacks *//*

        *//* check the left side of the pawn, minding the underflow File A *//*
        final long white_pawn_left_attack = (pawnPosition & BitboardUtils.createColumnClear(0)) << 7;

        *//* then check the right side of the pawn, minding the overflow File H *//*
        final long white_pawn_right_attack = (pawnPosition & BitboardUtils.createColumnClear(7)) << 9;

	    *//* the union of the left and right attacks together make up all the
		possible attacks *//*
        final long white_pawn_attacks = white_pawn_left_attack | white_pawn_right_attack;

        *//* Calculate where I can _actually_ attack something *//*
        final long white_pawn_valid_attacks = white_pawn_attacks & blackPieces;

	    *//* then we combine the two situations in which a white pawn can legally
		attack/move. *//*
        final long WhitePawnValid = white_pawn_valid_moves | white_pawn_valid_attacks;

        printBitboard(WhitePawnValid);
        return WhitePawnValid;

    }


    public static long computeBlackPawnMoves(final long pawnPosition, final long allPieces, final long whitePieces) {

        *//* check the single space infront of the black pawn *//*
        final long black_pawn_one_step = (pawnPosition >> 8) & ~allPieces;

	    *//* for all moves that came from rank 2 (home row) and passed the above
		filter, thereby being on rank 3, check and see if I can move forward
		one more *//*
        final long black_pawn_two_steps =
                ((black_pawn_one_step & BitboardUtils.createRowMask(5)) >> 8) & ~allPieces;

	    *//* the union of the movements dictate the possible moves forward
		available *//*
        final long black_pawn_valid_moves = black_pawn_one_step | black_pawn_two_steps;

        *//* next we calculate the pawn attacks *//*

        *//* check the left side of the pawn, minding the underflow File A *//*
        final long black_pawn_left_attack = (pawnPosition & BitboardUtils.createColumnClear(0)) >> 7;

        *//* then check the right side of the pawn, minding the overflow File H *//*
        final long black_pawn_right_attack = (pawnPosition & BitboardUtils.createColumnClear(7)) >> 9;

	    *//* the union of the left and right attacks together make up all the
		possible attacks *//*
        final long black_pawn_attacks = black_pawn_left_attack | black_pawn_right_attack;

        *//* Calculate where I can _actually_ attack something *//*
        final long black_pawn_valid_attacks = black_pawn_attacks & whitePieces;

	    *//* then we combine the two situations in which a black pawn can legally
		attack/move. *//*
        final long blackPawnValid = black_pawn_valid_moves | black_pawn_valid_attacks;

        BitboardUtils.printBitboard(blackPawnValid);
        return blackPawnValid;

    }

    public static long computeRookMoves(final long rookPosition, final long ownPieces, final long enemyPieces) {

        // TODO Temporary solution to check for H8 as H8 represents whether negative
        if (rookPosition > 0 || (rookPosition >> 1 != 0)) {
            // Move the rook up
            long allowedMoves = 0;

            final long rank = getRank(rookPosition);
            final long file = getFile(rookPosition);

            // Up
            for (long i = rank + 1; i < 8; i++) {
                final long upPosition = rookPosition << ((i - rank) * 8);
                if ((upPosition & ownPieces) > 0) {
                    break;
                } else if ((upPosition & enemyPieces) > 0) {
                    allowedMoves |= upPosition;
                    break;
                } else {
                    allowedMoves |= upPosition;
                }
            }

            // Down
            for (long i = rank - 1; i >= 0; i--) {
                final long downPosition = rookPosition >>> ((rank - i) * 8);
                if ((downPosition & ownPieces) > 0) {
                    break;
                } else if ((downPosition & enemyPieces) > 0) {
                    allowedMoves |= downPosition;
                    break;
                } else {
                    allowedMoves |= downPosition;
                }
            }

            // Right
            for (long i = file + 1; i < 8; i++) {
                final long rightPosition = rookPosition << i - file;
                if ((rightPosition & ownPieces) > 0) {
                    break;
                } else if ((rightPosition & enemyPieces) > 0) {
                    allowedMoves |= rightPosition;
                    break;
                } else {
                    allowedMoves |= rightPosition;
                }
            }

            // Left
            for (long i = file - 1; i >= 0; i--) {
                final long rightPosition = rookPosition >>> file - i;
                if ((rightPosition & ownPieces) > 0) {
                    break;
                } else if ((rightPosition & enemyPieces) > 0) {
                    allowedMoves |= rightPosition;
                    break;
                } else {
                    allowedMoves |= rightPosition;
                }
            }

            printBitboard(allowedMoves);
            return allowedMoves;
        } else {
            return 0;
        }
    }


    public static long computeBishopMoves(final long bishopPosition, final long ownPieces, final long enemyPieces) {

        // TODO Temporary solution to check for H8 as H8 represents whether negative
        if (bishopPosition > 0 || (bishopPosition >> 1 != 0)) {
            // Move the rook up
            long allowedMoves = 0;

            final long rank = getRank(bishopPosition);
            final long file = getFile(bishopPosition);

            // Up-left
            for (long i = rank + 1; i < 8; i++) {
                System.out.println(file - i);
                final long upRightPosition = bishopPosition << (((i - rank) * 8) - (i - rank));
                if ((upRightPosition & ownPieces) > 0) {
                    break;
                } else if ((upRightPosition & enemyPieces) > 0) {
                    allowedMoves |= upRightPosition;
                    break;
                } else {
                    allowedMoves |= upRightPosition;
                }
            }

            // Up-right
            for (long i = rank + 1; i < 8 && i - file >= 0; i++) {

                final long upRightPosition = bishopPosition << (((i - rank) * 8) + (i - rank));
                if ((upRightPosition & ownPieces) > 0) {
                    break;
                } else if ((upRightPosition & enemyPieces) > 0) {
                    allowedMoves |= upRightPosition;
                    break;
                } else {
                    allowedMoves |= upRightPosition;
                }
            }

            // Down-left
            for (long i = rank - 1; i >= 0 && 1 + i - file >= 0; i--) {
                final long downPosition = bishopPosition >>> ((rank - i) * 8) + (rank - i);
                if ((downPosition & ownPieces) > 0) {
                    break;
                } else if ((downPosition & enemyPieces) > 0) {
                    allowedMoves |= downPosition;
                    break;
                } else {
                    allowedMoves |= downPosition;
                }
            }

            // Down-right
            for (long i = rank - 1; i >= 0 && 2 + i - file >= 0; i--) {

                final long downPosition = bishopPosition >>> ((rank - i) * 8) - (rank - i);
                if ((downPosition & ownPieces) > 0) {
                    break;
                } else if ((downPosition & enemyPieces) > 0) {
                    allowedMoves |= downPosition;
                    break;
                } else {
                    allowedMoves |= downPosition;
                }
            }

            printBitboard(allowedMoves);
            return allowedMoves;
        } else {
            return 0;
        }
    }

    private static long getFile(final long position) {

        long currentPosition = 1L;
        long file = 0;
        while ((position & currentPosition) == 0) {
            currentPosition = currentPosition << 1;
            file++;
        }
        file %= 8;
        return file;
    }

    private static long getRank(final long position) {
        long currentPosition = 1L;
        long rank = 0;
        while ((position & currentPosition) == 0) {
            currentPosition = currentPosition << 1;
            rank++;
        }
        rank /= 8;
        return rank;
    }
*//*
    public static long computeBishopMoves(final long rookPosition, final long ownPieces, final long enemyPieces) {
        //  global rankmask, filemask
        final int ranknum = (int)rookPosition / 8;
        final int filenum = (int)(7 - rookPosition % 8);
        final long slider = 1 << rookPosition;
        final long horizontal = ((occupied - 2L * slider) ^ reverseBits(reverseBits(occupied) - 2 * reverseBits(slider))) & FILE_MASK[ranknum];
        final long vertical = (((occupied & FILE_MASK[filenum]) - 2 * slider) ^ reverseBits(reverseBits(occupied & FILE_MASK[filenum]) - 2 * reverseBits(slider))) & FILE_MASK[filenum];

        printBitboard(vertical ^ horizontal);
        return vertical ^ horizontal;
    }*//*

    private static long reverseBits(final long x) {
        long original = x;
        long b = 0;
        while (original != 0) {
            b <<= 1;
            b |= (x & 1);
            original >>= 1;
        }
        return b;
    }


    private static <T> List<List<T>> getBatches(final List<T> collection, final int batchSize) {
        return IntStream.iterate(0, i -> i < collection.size(), i -> i + batchSize)
                .mapToObj(i -> collection.subList(i, Math.min(i + batchSize, collection.size())))
                .collect(Collectors.toList());
    }*/
}
