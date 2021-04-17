package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.utils.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightsRepositoryImplTest {
    private FlightsRepository flightsRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private DateUtils dateUtils = new DateUtils();

    @BeforeEach
    void setUp(){
        flightsRepository = new FlightsRepositoryImpl("src/test/resources/FlightsTests/dbFlights.json");
    }
    @Test
    void loadDatabase() throws IOException {
        List<FlightDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/dbFlights.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest,flightsRepository.loadDatabase());
    }

    @Test
    void getAll() throws IOException {
        List<FlightDTO> listForTest = objectMapper.readValue(
                new File("src/test/resources/FlightsTests/dbFlights.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest,flightsRepository.getAll());
    }
}