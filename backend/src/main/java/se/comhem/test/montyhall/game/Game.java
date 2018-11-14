package se.comhem.test.montyhall.game;

public interface Game {

    /**
     * Make the initial door selection
     *
     * @param number Number of the door
     * @return Number of the door revealed by host upon first selection
     * @throws IllegalStateException if initial pick has already been made
     */
    DoorNumber initialPick(DoorNumber number);

    /**
     * Swap doors after reveal
     *
     * @throws IllegalStateException if initial pick has not been made yet or game has been finished already
     */
    void swap();

    /**
     * Complete game
     *
     * @return The content behind the selected door
     * @throws IllegalStateException if no initial pick has been made
     */
    Content finishAndGetContent();
}
