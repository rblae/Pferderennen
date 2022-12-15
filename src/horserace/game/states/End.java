package horserace.game.states;

import horserace.actors.Horse;
import horserace.actors.Player;
import horserace.game.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class End extends GameState {

    private final ArrayList<Player> players = game.getPlayers();
    private final ArrayList<Integer> ranking = game.getRanking();
    private final Horse[] horses = game.getHorses();

    public End(Game game) {
        super(game);
    }

    public void run() {
        printResults();
        checkBets();
        if (resetGame()) {
            game.setGameState(new Bets(game));
        } else {
            game.setGameState(null);
        }
    }

    private void printResults() {
        int rank = 1;

        System.out.println("""
                                 
                                 
                Hier ist das Ergebnis!
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃ Platz ┃ Name             ┃ Anzahl der Züge ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫""");
        for (int i : game.getRanking()) {
            System.out.printf("┃%5d. ┃ %-16s ┃ %15d ┃\n", rank++, horses[i].getHorseName(), horses[i].getMoves());
        }
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.printf("\nHerzlichen Glückwunsch, %s!\n\n\n", horses[game.getRanking().get(0)].getHorseName());
        Game.pause(6000);
    }

    private void checkBets() {
        for (Player player : players) {
            if (player.getLuckyHorse() == ranking.get(0)) {
                player.win();
            } else {
                player.lose();
                if (player.getCredit() <= 0) {
                    System.out.printf("%s hat leider kein Geld mehr und fliegt raus!\n\n", player.getName());
                }
            }
            Game.pause(2000);
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCredit() == 0) {
                players.remove(players.get(i));
                i--;
            }
        }
        Game.pause(6000);
    }

    private boolean resetGame() {
        Scanner scanner = new Scanner(System.in);

        if (players.size() > 0) {
            if (players.size() == 1) {
                System.out.printf("%s, willst du weiterspielen? (y/n)\n", players.get(0).getName());
            } else {
                System.out.println("\nWollt ihr weiterspielen? (y/n)");
            }

            while (true) {
                String choice = scanner.nextLine();
                if (choice.equals("y")) {
                    game.getRanking().clear();
                    System.out.println("\n\n");
                    return true;
                } else if (choice.equals("n")) {
                    System.out.println("\n\n");
                    return false;
                } else {
                    System.out.println("Gib bitte y oder n ein!");
                }
            }
        } else return false;
    }
}
