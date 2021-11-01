package com.vakolprod.app;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;

import java.util.LinkedList;

public class HelpTable {

        private final LinkedList<String> moves;

    public HelpTable(LinkedList<String> moves) {
        this.moves = moves;
    }

    public String[][] makeTable() {
        int size = moves.size() + 1;
        String[][] table = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = getTableCellValue(i, j);
            }
        }
        return table;
    }

    private String getTableCellValue(int i, int j){
        if (j == 0) {
            return i == 0 ? "PC \\ USER" : moves.get(i - 1);
        }
        return i == 0 ? moves.get(j - 1) : Rules.winnerCheck(moves, j, i);
    }

    public static void drawTable(LinkedList<String> movesList) {
        HelpTable helpTable = new HelpTable(movesList);
        String[][] table = helpTable.makeTable();
        AsciiTable drawTab = new AsciiTable();
        for (String[] row : table) {
            for (String cell: row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        for (String[] row: table) {
            drawTab.addRule();
            drawTab.addRow((Object[]) row);
        }
        drawTab.addRule();
        drawTab.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        System.out.println(drawTab.render());
    }
}