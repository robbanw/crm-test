package se.comhem.test.montyhall.game;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BasicGameTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * GAME LOGIC VALIDATION
     **/

    @Test
    public void testNonSwapGame() {
        Door[] doors = {new Door(Content.GOAT, DoorNumber.FIRST), new Door(Content.CAR, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        game.initialPick(DoorNumber.FIRST);

        assertEquals(Content.GOAT, game.finishAndGetContent());
    }

    @Test
    public void testSwapGame() {
        Door[] doors = {new Door(Content.GOAT, DoorNumber.FIRST), new Door(Content.CAR, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        game.initialPick(DoorNumber.FIRST);
        game.swap();

        assertEquals(Content.CAR, game.finishAndGetContent());
    }

    /**
     * INPUT VALIDATION
     **/

    @Test
    public void testSupplyIncorrectAmountOfDoors() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST)};

        exception.expect(IllegalArgumentException.class);
        new BasicGame(doors);
    }

    @Test
    public void testSupplyNoCars() {
        Door[] doors = {new Door(Content.GOAT, DoorNumber.FIRST), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        exception.expect(IllegalArgumentException.class);
        new BasicGame(doors);
    }

    @Test
    public void testSupplyTooManyCars() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST), new Door(Content.CAR, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        exception.expect(IllegalArgumentException.class);
        new BasicGame(doors);
    }

    @Test
    public void testSupplyOutOfOrder() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        exception.expect(IllegalArgumentException.class);
        new BasicGame(doors);
    }

    /**
     * STATE VALIDATION
     **/

    @Test
    public void testSwapWithoutStart() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        exception.expect(IllegalStateException.class);
        game.swap();
    }

    @Test
    public void testStartAfterFinish() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        game.initialPick(DoorNumber.FIRST);
        game.finishAndGetContent();

        exception.expect(IllegalStateException.class);
        game.initialPick(DoorNumber.FIRST);
    }

    @Test
    public void testSwapAfterFinish() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        game.initialPick(DoorNumber.FIRST);
        game.finishAndGetContent();

        exception.expect(IllegalStateException.class);
        game.swap();
    }

    @Test
    public void testFinishWithoutStart() {
        Door[] doors = {new Door(Content.CAR, DoorNumber.FIRST), new Door(Content.GOAT, DoorNumber.SECOND), new Door(Content.GOAT, DoorNumber.THIRD)};

        BasicGame game = new BasicGame(doors);

        exception.expect(IllegalStateException.class);
        game.finishAndGetContent();
    }
}
