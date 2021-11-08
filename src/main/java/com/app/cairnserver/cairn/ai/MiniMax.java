package com.app.cairnserver.cairn.ai;

public class MiniMax {

    /*

    function minimax(node, depth, isMaximizingPlayer, alpha, beta):

    if node is a leaf node :
        return value of the node

    if isMaximizingPlayer :
        bestVal = -INFINITY
        for each child node :
            value = minimax(node, depth+1, false, alpha, beta)
            bestVal = max( bestVal, value)
            alpha = max( alpha, bestVal)
            if beta <= alpha:
                break
        return bestVal

    else :
        bestVal = +INFINITY
        for each child node :
            value = minimax(node, depth+1, true, alpha, beta)
            bestVal = min( bestVal, value)
            beta = min( beta, bestVal)
            if beta <= alpha:
                break
        return bestVal
     */

    public static double miniMax(final Node state, final int depth, final boolean isMaximisingPlayer,  double alpha,  double beta) {

        if (state.isTerminal()) {
            return state.getUtility();
        }

        double bestVal;
        if (isMaximisingPlayer) {
            bestVal = -Double.MAX_VALUE;
            for (final Node node : state.getNodes()) {
                final double value = miniMax(node, depth + 1, false, alpha, beta);
                bestVal = Math.max(bestVal, value);
                alpha = Math.max(bestVal, alpha);
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            bestVal = Double.MAX_VALUE;
            for (final Node node : state.getNodes()) {
                final double value = miniMax(node, depth + 1, true, alpha, beta);
                bestVal = Math.min(bestVal, value);
                beta = Math.min(beta, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return bestVal;
    }
}
