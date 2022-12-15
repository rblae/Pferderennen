package horserace.actors;

import horserace.game.Game;

public class Racetrack {
    public static final int LENGTH = 20;

    public static void draw(Horse[] horses) {

        System.out.println("\n");
        for (int i = 0; i < Game.NUMBER_OF_HORSES; i++) {
            if (i == Game.NUMBER_OF_HORSES / 2) {
                System.out.printf("%16s   START ┣", horses[i].getHorseName());
            } else {
                System.out.printf("%16s         ┣", horses[i].getHorseName());
            }
            for (int j = 0; j <= LENGTH; j++) {
                int position = horses[i].getPosition();
                if (j == position) {
                    System.out.print("\uD83D\uDC34");
                } else if (j < position) {
                    System.out.print("━");
                } else {
                    System.out.print(" ");
                }
            }
            if (i == Game.NUMBER_OF_HORSES / 2) {
                System.out.print("┃ ZIEL\n");
            } else {
                System.out.print("┃\n");
            }
        }
    }
}
