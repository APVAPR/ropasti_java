package com.vakolprod.app;

import de.vandermeer.asciitable.AsciiTable;

public class HelpTable {

    public static void makeTable() {
        AsciiTable helpTab = new AsciiTable();
        helpTab.addRule();
        helpTab.addRow("!!!", "row 1 col 1");
        System.out.println(helpTab.render());
    }
}
