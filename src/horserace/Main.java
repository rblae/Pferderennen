package horserace;

import horserace.game.Game;

public class Main {
    private static void greetPlayers() {
        System.out.println("Willkommen auf der Pferderennbahn!");
        Game.pause(2000);
        System.out.println("In diesem Spiel könnt ihr Geld auf ein Pferd eurer Wahl setzen! Jeder Spieler startet dabei mit 10 €");
        Game.pause(3000);
        System.out.println("Bei einem Sieg erhaltet ihr euer Geld dreifach zurück!");
    }

    private static void sayGoodbye() {
        System.out.println("\n\n\nVielen Dank für's Spielen!");
        Game.pause(2000);
        System.out.println("Bis zum nächsten Mal!");
        Game.pause(2000);
    }

    public static void main(String[] args) {
        Game game = Game.getGame();

        greetPlayers();
        Game.pause(3000);
        while (game.getGameState() != null) {
            game.run();
        }
        sayGoodbye();
    }
}
