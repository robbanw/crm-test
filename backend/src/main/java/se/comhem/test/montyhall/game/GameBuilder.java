package se.comhem.test.montyhall.game;

import java.util.Random;

public class GameBuilder {
    private static final Random random = new Random();
    private static final DoorNumber[] doorNumbers = DoorNumber.values();

    /**
     * @return A game instance with three randomized doors
     */
    public static Game getBasicGame() {
        Door[] doors = new Door[3];
        int carDoorNum = random.nextInt(doors.length);
        for (int i = 0; i < doors.length; i++) {
            if (i == carDoorNum) {
                doors[i] = new Door(Content.CAR, doorNumbers[i]);
            } else {
                doors[i] = new Door(Content.GOAT, doorNumbers[i]);
            }
        }

        return new BasicGame(doors);
    }
}
