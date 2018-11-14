package se.comhem.test.montyhall.service;

import org.springframework.stereotype.Service;
import se.comhem.test.montyhall.dto.SimulationResultDTO;
import se.comhem.test.montyhall.game.DoorNumber;
import se.comhem.test.montyhall.game.Game;
import se.comhem.test.montyhall.game.GameBuilder;

import java.util.Random;

@Service
public class SimulationService {
    private final Random random = new Random();
    private final DoorNumber[] doorNumbers = DoorNumber.values();

    /**
     * Runs simulations of the monty hall game.
     *
     * @param numSimulations Number of simulations to run
     * @param switchDoor     Set to true to simulate with door swap
     * @return Total results of simulations
     */
    public SimulationResultDTO runSimulations(int numSimulations, boolean switchDoor) {
        int numGoats = 0, numCars = 0;

        while (numSimulations-- > 0) {
            Game game = GameBuilder.getBasicGame();
            DoorNumber selectedDoor = doorNumbers[random.nextInt(doorNumbers.length)];
            game.initialPick(selectedDoor);
            if (switchDoor) {
                game.swap();
            }

            switch (game.finishAndGetContent()) {
                case CAR:
                    numCars++;
                    break;
                case GOAT:
                    numGoats++;
                    break;
            }
        }

        return new SimulationResultDTO(numCars, numGoats);
    }
}
