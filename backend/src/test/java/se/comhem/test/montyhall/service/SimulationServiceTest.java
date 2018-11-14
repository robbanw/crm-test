package se.comhem.test.montyhall.service;

import org.junit.Test;
import se.comhem.test.montyhall.dto.SimulationResultDTO;

import static org.junit.Assert.assertEquals;

public class SimulationServiceTest {

    @Test
    public void testNonSwapSimulation() {
        SimulationService simulationService = new SimulationService();

        SimulationResultDTO result = simulationService.runSimulations(10, false);

        assertEquals(10, result.getNumGoats() + result.getNumCars());
    }

    @Test
    public void testSwapSimulation() {
        SimulationService simulationService = new SimulationService();

        SimulationResultDTO result = simulationService.runSimulations(10, true);

        assertEquals(10, result.getNumGoats() + result.getNumCars());
    }
}
