package se.comhem.test.montyhall.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.comhem.test.montyhall.dto.SimulationResultDTO;
import se.comhem.test.montyhall.service.SimulationService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController("/simulate")
@Validated
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping
    public SimulationResultDTO runSimulation(@Valid @RequestParam @Min(value = 1, message = "Must run at least one simulation")
                                             @Max(value = 10_000_000, message = "Max amount of simulations is 10 million") int numSimulations,
                                             @RequestParam boolean switchDoor) {
        return simulationService.runSimulations(numSimulations, switchDoor);
    }
}
