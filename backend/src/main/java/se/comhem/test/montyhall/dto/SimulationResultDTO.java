package se.comhem.test.montyhall.dto;

public class SimulationResultDTO {

    private int numCars;
    private int numGoats;

    public SimulationResultDTO() {
        // For serialization
    }

    public SimulationResultDTO(int numCars, int numGoats) {
        this.numCars = numCars;
        this.numGoats = numGoats;
    }

    public int getNumCars() {
        return numCars;
    }

    public int getNumGoats() {
        return numGoats;
    }
}
