package horserace.game.states;

import horserace.actors.Horse;
import horserace.actors.Player;
import horserace.game.Game;

import java.util.ArrayList;

public class Bets extends GameState {
    private final ArrayList<Player> players = game.getPlayers();
    Horse[] horses = game.getHorses();

    public Bets(Game game) {
        super(game);
    }

    public void run() {
        createHorses();
        placeBets();
        tellBets();
        game.setGameState(new Running(game));
    }

    private void createHorses() {
        Horse[] horses = game.generateNewSetOfHorses();

        System.out.println("Heute treten diese Pferde an:");
        for (int i = 0; i < horses.length; i++) {
            System.out.printf("Nr. %2d: %s\n", i + 1, horses[i].getHorseName());
        }
        Game.pause(3000);
    }

    private void placeBets() {
        for (Player player : players) {
            System.out.printf("\n%s, auf welches Pferd willst du dein Geld setzen?\n", player.getName());
            player.setLuckyHorse();
            System.out.printf("Du hast noch %.2f €\n", player.getCredit());
            Game.pause(2000);
            System.out.print("Wie viel möchtest du setzen?\n");
            player.placeBet();
        }
        Game.pause(2000);
    }

    private void tellBets() {
        if (players.size() == 1) {
            System.out.println("\nRien ne va plus! Alle Wetten wurden gesetzt!\nHier ist deine Wette:");
        } else {
            System.out.println("\nRien ne va plus! Alle Wetten wurden gesetzt!\nHier sind eure Wetten:");
        }
        Game.pause(2000);
        for (Player player : players) {
            System.out.printf("%s setzt %.2f € auf %s\n", player.getName(), player.getCurrentBet(), horses[player.getLuckyHorse()].getHorseName());
        }
        Game.pause(2000);

        if (players.size() == 1) {
            System.out.println("\nBist du bereit?");
        } else {
            System.out.println("\nSeid ihr bereit?");
        }
        Game.pause(1500);
        System.out.println("3...");
        Game.pause(1000);
        System.out.println("2...");
        Game.pause(1000);
        System.out.println("1...");
        Game.pause(1000);
    }
}
