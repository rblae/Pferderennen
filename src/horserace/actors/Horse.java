package horserace.actors;

import horserace.game.Game;

import java.util.Random;

public class Horse extends Thread {
    private final int id;
    private String name;
    private int position = 0;
    private int moves = 0;

    public Horse(int id) {
        Random random = new Random();

        String[] namePool = {"Ahmed", "Andrea", "Andreas", "Andrés", "Anke", "Annett", "Anton", "Arne", "Baitong", "Bastian",
                "Bert", "Bilal", "Birgit", "Burak", "Carola", "Carsten", "Chang-Huei", "Christian", "Christiane", "Christoph",
                "Cihan", "Dagmar", "Daniel", "Dominik", "Eduard", "Elke", "Ferris", "Florian", "Francescoli", "Frank",
                "Friedrich", "Gökay", "Hans-Jürgen", "Heike", "Henning", "Henrietta", "Holger", "Inga", "Ivan", "Jakob",
                "Jakub", "Jan-Frederic", "Janina", "Jan-Niclas", "Jeannette", "Jennifer", "Jonas", "Jörg", "Julian", "Jürgen",
                "Karoline", "Karsten", "Katharina", "Klaudia", "Kuldeep-Johannes", "Kyrill", "Lars", "Lilia", "Linus", "Luisa",
                "Lukas", "Lynn", "Marcel", "Marcus", "Marie-Luise", "Mario", "Martin", "Marvin", "Matthias", "Mert",
                "Michael", "Moin", "Monique", "Moonkyung", "Muhammed", "Mustafa", "Nabeel", "Nathanael", "Niels", "Nikolas",
                "Nino", "Pascal", "Philipp", "Rafael", "Rebecca", "Ricarda", "Rico", "Robert", "Ron", "Roshan",
                "Sarah", "Sascha", "Sebastian", "Sirko", "Stefan", "Steven", "Sven", "Tapani", "Tatjana", "Thai-Le",
                "Theo", "Theresa", "Thilak", "Thomas", "Thuy Linh", "Tobias", "Tom", "Tomasz", "Vanessa", "Vildana",
                "Vivien", "Walburga", "Waldemar"};

        this.id = id;
        do {
            name = namePool[random.nextInt(namePool.length)];
        } while (isDuplicate());
    }

    public void run() {
        Random random = new Random();

        while (!(position == Racetrack.LENGTH)) {
            int nextPosition = position + random.nextInt(1, 4);
            if (nextPosition > Racetrack.LENGTH) {
                nextPosition = Racetrack.LENGTH;
            }
            position = nextPosition;
            moves++;
            Game.pause(Game.GAME_SPEED);
        }
        Game.getGame().addToRanking(id);
    }

    private boolean isDuplicate() {
        Horse[] horses = Game.getGame().getHorses();

        for (int i = 0; i < id; i++) {
            if (horses[i].getHorseName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getPosition() {
        return position;
    }

    public int getMoves() {
        return moves;
    }

    public String getHorseName() {
        return name;
    }
}
