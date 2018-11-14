package se.comhem.test.montyhall.game;

import java.util.Objects;

public class Door {

    private final Content hiddenContent;
    private final DoorNumber doorNumber;

    public Door(Content hiddenContent, DoorNumber doorNumber) {
        if (hiddenContent == null || doorNumber == null) {
            throw new IllegalArgumentException("Door content and number must not be null");
        }

        this.hiddenContent = hiddenContent;
        this.doorNumber = doorNumber;
    }

    public Content getHiddenContent() {
        return hiddenContent;
    }

    public DoorNumber getDoorNumber() {
        return doorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Door door = (Door) o;
        return hiddenContent == door.hiddenContent && doorNumber == door.doorNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hiddenContent, doorNumber);
    }
}
