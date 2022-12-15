package horserace.game;

import horserace.actors.Horse;
import horserace.actors.Player;
import horserace.game.states.GameState;
import horserace.game.states.Start;

import java.util.ArrayList;

public class Game {
    public static final int NUMBER_OF_HORSES = 10; //Maximal 113 - Anzahl der verfügbaren Namen
    public static final int GAME_SPEED = 1250;
    private static final Game GAME = new Game();
    private final Horse[] horses = new Horse[NUMBER_OF_HORSES];
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Integer> ranking = new ArrayList<>();

    private GameState gameState;

    private Game() {
        gameState = new Start(this);
    }

    public void run() {
        gameState.run();
    }

    public Horse[] generateNewSetOfHorses() {
        for (int id = 0; id < NUMBER_OF_HORSES; id++) {
            horses[id] = new Horse(id);
        }
        return horses;
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void addToRanking(int id) {
        ranking.add(id);
    }

    public static Game getGame() {
        return GAME;
    }

    public Horse[] getHorses() {
        return horses;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setGameState(GameState newState) {
        gameState = newState;
    }

    public ArrayList<Integer> getRanking() {
        return ranking;
    }

    public GameState getGameState() {
        return gameState;
    }
}
