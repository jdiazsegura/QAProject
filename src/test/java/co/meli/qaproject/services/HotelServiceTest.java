package co.meli.qaproject.services;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.repositories.HotelsRepository;
import co.meli.qaproject.repositories.HotelsRepositoryImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HotelServiceTest {

    private HotelsRepository hotelsRepository = mock(HotelsRepository.class);

    @InjectMocks
    private HotelService hotelService;

    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        hotelService = new HotelServiceImpl(hotelsRepository);
    }

    @Test
    void getAll() throws IOException {
        List<HotelDTO> testList = objectMapper.readValue(
                new File("src/test/resources/dbHotels.json"),
                new TypeReference<>() {
                }
        );
        when(hotelsRepository.getAll()).thenReturn(testList);

        Assertions.assertNotNull(hotelService.getAll());
    }
}