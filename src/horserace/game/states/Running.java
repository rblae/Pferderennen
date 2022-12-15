package horserace.game.states;

import horserace.actors.Horse;
import horserace.actors.Racetrack;
import horserace.game.Game;

import java.util.Arrays;

public class Running extends GameState {
    Horse[] horses = game.getHorses();
    public Running(Game game) {
        super(game);
    }

    public void run() {
        race();
        game.setGameState(new End(game));
    }

    private void race() {
        Racetrack.draw(horses);
        Game.pause(Game.GAME_SPEED);

        Arrays.stream(horses).forEach(Thread::start);

        while (!hasEveryoneFinished()) {
            Racetrack.draw(horses);
            Game.pause(Game.GAME_SPEED);
        }
    }

    private boolean hasEveryoneFinished() {
        for (int i = 0; i < Game.NUMBER_OF_HORSES; i++) {
            if (horses[i].isAlive()) {
                return false;
            }
        }
        return true;
    }
}
