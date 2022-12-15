package horserace.game.states;

import horserace.actors.Player;
import horserace.game.Game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Start extends GameState {
    ArrayList<Player> players = game.getPlayers();

    public Start(Game game) {
        super(game);
    }

    public void run() {
        createPlayers(readNumberOfPlayers());
        game.setGameState(new Bets(game));
    }

    private int readNumberOfPlayers() {
        int numberOfPlayers = 0;
        Scanner scanner = new Scanner(System.in);
        int MAX_NUMBER_OF_PLAYERS = 4;

        System.out.printf("\nWie viele Spieler seid ihr? (1-%d)\n", MAX_NUMBER_OF_PLAYERS);
        while (numberOfPlayers < 1 || numberOfPlayers > MAX_NUMBER_OF_PLAYERS) {
            try {
                numberOfPlayers = scanner.nextInt();
                if (numberOfPlayers < 1 || numberOfPlayers > MAX_NUMBER_OF_PLAYERS) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.printf("Gebt eine Zahl zwischen 1-%d ein:\n", MAX_NUMBER_OF_PLAYERS);
                scanner.nextLine();
            }
        }

        return numberOfPlayers;
    }

    private void createPlayers(int numberOfPlayers) {
        Scanner scanner = new Scanner(System.in);
        final int MAX_NAME_LENGTH = 20;
        String name;

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("\nSpieler %d, wie ist dein Name?\n", i + 1);
            name = scanner.next();
            if (name.length() > MAX_NAME_LENGTH) {
                name = name.substring(0, MAX_NAME_LENGTH);
            }
            Player player = new Player(name);
            players.add(player);
            System.out.printf("%s wurde registriert!\n\n", players.get(i).getName());
            Game.pause(1000);
        }
        System.out.println("Los geht's!");
        Game.pause(3000);
    }
}
