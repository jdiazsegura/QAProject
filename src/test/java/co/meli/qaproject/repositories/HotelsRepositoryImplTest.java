package co.meli.qaproject.repositories;

import co.meli.qaproject.dto.HotelDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


class HotelsRepositoryImplTest {

    private HotelsRepository hotelsRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        hotelsRepository = new HotelsRepositoryImpl("src/main/resources/dbHotels.json");
    }


    @Test
    void loadDatabase() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.loadDatabase());
    }

    @Test
    @Disabled
    void loadDatabaseExceptionTest() {
        hotelsRepository = new HotelsRepositoryImpl("asd");
        Exception notFound = Assertions.assertThrows(FileNotFoundException.class,
                () -> hotelsRepository.loadDatabase());
    }

    @Test
    void getAll() throws IOException {
        List<HotelDTO> listForTest = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });

        Assertions.assertNotNull(listForTest);
        Assertions.assertEquals(listForTest, hotelsRepository.getAll());
    }
}