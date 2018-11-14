package se.comhem.test.montyhall;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.comhem.test.montyhall.dto.SimulationResultDTO;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MontyHallApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateSimulationReturnsResults() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/simulate?numSimulations=100&switchDoor=false")).andExpect(status().isOk())
                .andReturn();

        SimulationResultDTO simulationResultDTO = new ObjectMapper().readValue(result.getResponse().getContentAsByteArray(), SimulationResultDTO.class);

        assertEquals(100, simulationResultDTO.getNumCars() + simulationResultDTO.getNumGoats());
    }

    @Test
    public void badRequestOnMissingInput() throws Exception {
        this.mockMvc.perform(get("/simulate")).andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestOnInvalidInput() throws Exception {
        this.mockMvc.perform(get("/simulate?numSimulations=0&switchDoor=false")).andExpect(status().isBadRequest());
    }
}
