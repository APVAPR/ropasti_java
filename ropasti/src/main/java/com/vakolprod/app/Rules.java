package com.vakolprod.app;

import java.util.LinkedList;

public class Rules {

    public static String winnerCheck(LinkedList<String> listSigns, int userMove, int PCmove) {
        int half;
        if (userMove == PCmove) return "Draw";
        half = listSigns.size() / 2;
        if (userMove > PCmove){
            if (userMove - PCmove <= half) return "Win";
            return "Lose";
        } else {
            if (PCmove - userMove <= half) {
                return "Lose";
            }
            return "Win";
        }
    }

}
