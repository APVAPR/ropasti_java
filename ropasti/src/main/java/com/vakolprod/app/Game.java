package com.vakolprod.app;

import java.util.*;

public class Game {

    LinkedList<String> listSigns;
    String userMove;
    int userIndex;
    int compIndex;
    String compMove;

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

    private void compMove() {
        Random number = new Random();
        this.compIndex = number.nextInt(this.listSigns.size());
        this.compMove = this.listSigns.get(this.compIndex);
    }

    private void userMoveInput() {
        Scanner mv = new Scanner(System.in);
        System.out.println("Enter your move: ");
        this.userMove = mv.nextLine();
    }

    public void checkUserInput() {
        if (Objects.equals(this.userMove, "?")) {
            HelpTable.drawTable(this.listSigns);
            gameRound();

        }
        else if (Objects.equals(this.userMove, "0")) {
            System.out.println("GAME FINISHED");
            System.exit(0);
        } else {
            try {
                Integer.parseInt(userMove);
                getUserIndex();
            } catch (NumberFormatException e) {
                System.out.println("'!!!You entered invalid values!!!!'");
            }
        }
    }

    public void getUserIndex() {
        int index = Integer.parseInt(this.userMove) - 1;
        if (index >= 0 && index < (this.listSigns.size())) {
            this.userIndex = index;
            this.userMove = this.listSigns.get(index + 1);
            System.out.println("Your move: " + this.listSigns.get(index));
        }
    }

    public static void inputInfo() {
        System.out.println("Invalid input:" + "\n" +
                "'The number of characters must be odd and more than three'"+ "\n" +
                "'Signs must not be repeated"+ "\n" +
                "'Example input: rock paper scissor lizard spock");
    }

    public void gameRound() {
        showMenu();
        userMoveInput();
        checkUserInput();
    }

    public static boolean checkInputRules(String[] args) {
        return args.length < 3 || !(checkReplays(args)) || (args.length % 2 == 0);
    }

    public static void main(String[] args) {
        if (checkInputRules(args)) inputInfo();
        else {
            LinkedList<String> signList = new LinkedList<>(List.of(args));
            Game game = new Game(signList);
            game.compMove();
            HashGen.genKeyandHmac(game.compMove);
            String key = HashGen.key;
            String HMAC = HashGen.getHMAC();
            System.out.println("HMAC: " + HMAC);
            game.gameRound();
            System.out.println("Computer move: " + game.compMove);
            String winner = Rules.winnerCheck(game.listSigns, game.userIndex, game.compIndex);
            if (Objects.equals(winner, "Draw")) {
                System.out.println("Draw");
            } else {
                System.out.println("You " + winner);
                System.out.println("KEY: " + key);
            }
        }
    }
}