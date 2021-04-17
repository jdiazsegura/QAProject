package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.exceptions.IncorrectFormatException;
import co.meli.qaproject.exceptions.NoValidDatesException;
import co.meli.qaproject.services.HotelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    private HotelService hotelService = mock(HotelService.class);

    @InjectMocks
    private IMainController hotelController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<HotelDTO> testList;

    @BeforeEach
    void setUp() throws IOException {
        hotelController = new MainController();
        testList = objectMapper.readValue(
                new File("src/test/resources/dbHotels.json"),
                new TypeReference<>() {
                }
        );
    }

    @Test
    void get() throws NoValidDatesException, IncorrectFormatException {
        when(hotelService.getAll()).thenReturn(testList);
        var flag = hotelController.get(null,null,null);
        var responseTest = new ResponseEntity<List<HotelDTO>>(testList, HttpStatus.OK);

        assertEquals(responseTest,flag);

    }

    @Test
    void bookingHotel() {
    }
}