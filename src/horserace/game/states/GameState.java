package horserace.game.states;

import horserace.game.Game;

public abstract class GameState {
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract void run();
}
