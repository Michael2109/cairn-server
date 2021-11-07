package com.app.cairnserver.cairn.ai;

import java.util.Comparator;

public final class MinMaxTemplate {

    private MinMaxTemplate() {}

    public static State minimaxDecision(State state) {
        return state.getActions().stream()
                .max(Comparator.comparing(MinMaxTemplate::minValue)).get();
    }

    private static double maxValue(State state) {
        if(state.isTerminal()){
            return state.getUtility();
        }
        return state.getActions().stream()
                .map(MinMaxTemplate::minValue)
                .max(Comparator.comparing(Double::valueOf)).get();
    }

    private static double minValue(State state) {
        if(state.isTerminal()){
            return state.getUtility();
        }
        return state.getActions().stream()
                .map(MinMaxTemplate::maxValue)
                .min(Comparator.comparing(Double::valueOf)).get();
    }
}
