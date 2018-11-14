package se.comhem.test.montyhall.game;

import java.util.Arrays;

public class BasicGame implements Game {

    private final Door[] doors;
    private Door selectedDoor;
    private Door revealedDoor;
    private boolean finished;

    /**
     * @param doors Number of doors supplied must be exactly 3 and must contain one and only one door with Content.CAR as hidden content.
     *              Doors must be in order of their DoorNumber in the array.
     */
    BasicGame(Door[] doors) {
        validateDoors(doors);
        this.doors = doors;
    }

    @Override
    public DoorNumber initialPick(DoorNumber number) {
        if (selectedDoor != null) {
            throw new IllegalStateException("Initial pick has already been made");
        }

        selectedDoor = doors[number.ordinal()];

        revealedDoor = Arrays.stream(doors).filter(door -> door.getHiddenContent() != Content.CAR && !door.equals(selectedDoor))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        return revealedDoor.getDoorNumber();
    }

    @Override
    public void swap() {
        if (selectedDoor == null) {
            throw new IllegalStateException("Initial pick has not been made");
        }
        if (finished) {
            throw new IllegalStateException("Game is already finished");
        }

        selectedDoor = Arrays.stream(doors).filter(door -> !door.equals(revealedDoor) && !door.equals(selectedDoor))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public Content finishAndGetContent() {
        if (selectedDoor == null) {
            throw new IllegalStateException("Initial pick has not been made");
        }

        finished = true;
        return selectedDoor.getHiddenContent();
    }

    private void validateDoors(Door[] doors) {
        if (doors.length != 3) {
            throw new IllegalArgumentException("Must supply exactly three doors");
        }

        int numCars = 0;
        for (int i = 0; i < doors.length; i++) {
            if (doors[i] == null) {
                throw new IllegalArgumentException("Doors must not be null");
            }
            if (i != doors[i].getDoorNumber().ordinal()) {
                throw new IllegalArgumentException("DoorNumber must match position in input array");
            }

            if (doors[i].getHiddenContent() == Content.CAR) {
                numCars++;
            }
        }

        if (numCars != 1) {
            throw new IllegalArgumentException("Must supply exactly one door with car content");
        }
    }
}
