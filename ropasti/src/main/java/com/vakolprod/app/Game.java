package com.vakolprod.app;

import java.util.*;

public class Game {

    LinkedList<String> listSigns;

    Game(LinkedList<String> signs) {
        this.listSigns = signs;

    }
    public static boolean checkReplays(String[] movesChain) {
        return (new HashSet<>(Arrays.asList(movesChain)).size() == movesChain.length);
    }

    public void showMenu() {
        for (int i = 0; i < this.listSigns.size(); i++) System.out.println((i + 1) + " - " + this.listSigns.get(i));
        System.out.println("0" + " - " + "exit");
        System.out.println("?" + " - " + "help");
    }

    private int compMove(LinkedList<String> list) {
        Random number = new Random();
        return number.nextInt(list.size());
    }

    private int userMove() {
        Scanner mv = new Scanner(System.in);
        System.out.println("Enter your move: ");
        String move = mv.nextLine();
        boolean success = true;
        if (Objects.equals(move, "?")) return -1;
        else if (Objects.equals(move, "0")) {
            System.out.println("GAME FINISHED");
            System.exit(0);
        }
        else {
            try {
                Integer.parseInt(move);
            } catch (NumberFormatException e) {
                success = false;
            }
            if (success) {
                int index = Integer.parseInt(move) - 1;
                if (index >= 0 && index < (this.listSigns.size())) {
                    System.out.println("Your move: " + this.listSigns.get(index));
                    return index;
                }
            }
        }
        return -3;
    }
    public static void inputInfo() {
        System.out.println("Invalid input:" + "\n" +
                "'The number of characters must be odd and more than three'"+ "\n" +
                "'Signs must not be repeated"+ "\n" +
                "'Example input: rock paper scissor lizard spock");
    }

    public int gameRound() {
        this.showMenu();
        int user = this.userMove();
        boolean status = false;
        if (user == -3) {
            System.out.println("'!!!You entered invalid values!!!!'");
        } else if (user == -1) {
            System.out.println("------HELP-TABLE------");
            HelpTable.makeTable();
        } else if (user >= 0 && user < (this.listSigns.size())) status = true;
        if (status) return user;
        return -1;
    }
    public static boolean checkInputRules(String[] args) {
        return args.length < 3 || !(checkReplays(args)) || (args.length % 2 == 0);
    }

    public static void main(String[] args) {
        if (checkInputRules(args)) Game.inputInfo();
        else {
            LinkedList<String> signList = new LinkedList<>(List.of(args));
            Game game = new Game(signList);
            int pcInd = game.compMove(game.listSigns);
            String compMoveIs = game.listSigns.get(pcInd);
            HashGen.genKeyandHmac(compMoveIs);
            String key = HashGen.key;
            String HMAC = HashGen.getHMAC();
            System.out.println("HMAC: " + HMAC);
            int userMove = game.gameRound();
            while (userMove < 0) {
                userMove = game.gameRound();
            }
            System.out.println("Computer move: " + compMoveIs);
            String winner = Rules.winnerCheck(game.listSigns, userMove, pcInd);
            if (Objects.equals(winner, "Draw")) {
                System.out.println("Draw");
            } else {
                System.out.println("You " + winner);
                System.out.println("KEY: " + key);
            }
        }
    }
}