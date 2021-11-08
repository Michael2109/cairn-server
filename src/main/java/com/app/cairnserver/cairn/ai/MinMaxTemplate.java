package com.app.cairnserver.cairn.ai;

import java.util.Comparator;

public final class MinMaxTemplate {

    private MinMaxTemplate() {}

    public static Node minimaxDecision(final Node node) {
        return node.getNodes().stream()
                .max(Comparator.comparing(childNode -> MiniMax.miniMax(childNode, 0, true, -Double.MAX_VALUE, Double.MAX_VALUE))).get();
    }
}
