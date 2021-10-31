package com.vakolprod.app;

import com.jakewharton.fliptables.FlipTable;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HelpTable {



//    public static String[][] makeTable(LinkedList<String> moves) {
//        int size = moves.size() + 1;
//        String[][] table = new String[size][size];
//        for (int y = 0; y < size; y++)
//        {
//            for (int x = 0; x < size; x++)
//            {
//                if (x == 0)
//                {
//                    if (y == 0)
//                        table[y][x] = "PC \\ USER";
//                    else
//                        table[y][x] = moves.get(y);
//                }
//                else if (y == 0)
//                    table[y][x] = moves.get(x);
//                else
//                    table[y][x] = Rules.winnerCheck(moves, y, x);
//            }
//        }
//        return table;
//    }


//    public String[][] makeTable(LinkedList<String> moves) {
//        int size = moves.size() + 1;
//        String[][] table = new String[size][size];
//        for (int y = 0; y < size; y++)
//        {
//            for (int x = 0; x < size; x++)
//            {
//                table[y][x] = getTableCellValue(y, x);
//            }
//        }
//        return table;
//    }
//
//    private void getTableCellValue(int y, int x){
//        if (x == 0)
//        {
//            if (y == 0)
//                return "PC \\ USER";
//            return moves.get(y);
//        }
//        if (y == 0)
//            return moves.get(x);
//        return Rules.winnerCheck(moves, y, x);
//    }
//
//    private void getTableCellValue(int y, int x){
//        if (x == 0)
//        {
//            return y == 0 ? "PC \\ USER" : moves.get(y);
//        }
//        return y==0 ? moves.get(x) : Rules.winnerCheck(moves, y, x);
//    }

    public static String[][] makeTable(LinkedList<String> moves) {
        String[][] table = new String[moves.size() + 1][moves.size() + 1];
        table[0][0] = "PC \\ USER";
        for (int i = 1; i <= moves.size(); i++) {
            table[0][i] = moves.get(i-1);
            table[i][0] = moves.get(i-1);
        }
        for (int i = 1; i <= moves.size(); i++) {
            for (int j = 1; j <= moves.size(); j++) {
                table[i][j] = Rules.winnerCheck(moves, j, i);
            }
        }return table;
    }

    public static void drawTable(LinkedList<String> moves) {
        String[][] table = makeTable(moves);
        AsciiTable drawTab = new AsciiTable();
        for (String[] row: table) {
            drawTab.addRule();
            drawTab.addRow(row);
        }
        drawTab.addRule();
        drawTab.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        System.out.println(drawTab.render());
    }
}




