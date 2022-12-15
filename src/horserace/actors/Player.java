package horserace.actors;

import horserace.game.Game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private final String name;
    private double credit;
    private double currentBet;
    private int luckyHorse;

    public Player(String name) {
        this.name = name;
        credit = 10;
        currentBet = 0;
    }

    public void placeBet() {
        currentBet = readBet();
        credit -= currentBet;
    }

    private double readBet() {
        double bet = 0;
        Scanner scanner = new Scanner(System.in);

        while (bet <= 0 || bet > credit) {
            try {
                bet = scanner.nextDouble();
                if (bet <= 0 || bet > credit) {
                    throw new InputMismatchException();
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Gib bitte einen gültigen Wert an: ");
            }
        }
        bet *= 100;
        bet -= bet % 1;
        bet /= 100;

        return bet;
    }

    public void setLuckyHorse() {
        luckyHorse = 0;
        Scanner scanner = new Scanner(System.in);

        while (luckyHorse <= 0 || luckyHorse > Game.NUMBER_OF_HORSES) {
            try {
                luckyHorse = scanner.nextInt();
                if (luckyHorse <= 0 || luckyHorse > Game.NUMBER_OF_HORSES) {
                    throw new InputMismatchException();
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Gib bitte einen gültigen Wert an: ");
            }
        }
        luckyHorse--;
    }

    public void win() {
        Random random = new Random();
        String[] winningMessages = {
                "Ich fasse es nicht! Ich habe gewonnen!",
                "War ja klar, dass ich gewinne.",
                "Hurra! Davon kaufe ich mir noch mehr Wettscheine!",
                "Gewonnen! Meine Berechnungen haben sich gelohnt!",
                "Ich hab ja eigentlich auf Niederlage gespielt, aber na gut.",
                "Super! Das ganze Geld legen ich in Immobilien an!",
                "Kann mir jemand den Gewinn in Döner umrechnen?"};
        int randomInt = random.nextInt(winningMessages.length);

        credit += 3 * currentBet;
        System.out.printf("%s: %s\n", name, winningMessages[randomInt]);
        System.out.printf("Dein neuer Kontostand beträgt: %4.2f €\n\n", credit);
    }

    public void lose() {
        Random random = new Random();
        String[] losingMessages = {
                "Meine Frau wird mich umbringen! Oder mein Mann. Oder sogar beide!",
                "Verloren! Aber egal, ich hab Schotter.",
                "Oh je... Zeit, das Sparschwein meiner Tochter zu plündern...",
                "Das kann nicht sein! Ich hatte einen todsicheren Tipp!",
                "Och nein, verloren. Vielleicht sollte ich lieber aufhören?",
                "Das Rennen wurde doch manipuliert! Wo ist der Buchhalter?!",
                "So eine Frechheit! Das können sie mir nicht antun!",
                "Hallo? Polizei? Hier sind Raubritter am Werk!",
                "Das ich das noch erleben darf! Ich hab in meinem ganzen Leben noch nie verloren!",
                "Wie oft habe ich jetzt schon verloren? Ich hoffe, ich entwickel keine Spielsucht!",
                "Ich kann nicht verlieren! Sie hören von meinem Anwalt!",
                "Verloren... Ja, gut, was hatte ich eigentlich auch erwartet?",
                "Wie konnte ich verlieren? Hat sich die Lineare Algebra etwas nicht ausgezahlt?",
                "Ach das war ja klar. Ich konnte Pferde noch nie leiden und sie mich nicht.",
                "Oh nein, mein ganzes Taschengeld... Deshalb ist Glücksspiel also ab 18!",
                "Wenn ich die Augen schließe ist mein Verlust nicht mehr da!"
        };
        int randomInt = random.nextInt(losingMessages.length);

        currentBet = 0;
        System.out.printf("\n%s: %s\n", name, losingMessages[randomInt]);
        System.out.printf("Dein neuer Kontostand beträgt: %4.2f €\n", credit);
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public int getLuckyHorse() {
        return luckyHorse;
    }
}
