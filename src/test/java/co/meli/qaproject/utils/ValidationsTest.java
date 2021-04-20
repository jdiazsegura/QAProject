package co.meli.qaproject.utils;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.FlightReservationDTO;
import co.meli.qaproject.dto.flights.PayloadFlightReservDTO;
import co.meli.qaproject.dto.hotels.BookingDTO;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.exceptions.ApiException;
import co.meli.qaproject.repositories.FlightsRepository;
import co.meli.qaproject.repositories.HotelsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ValidationsTest {


    ObjectMapper objectMapper = new ObjectMapper();
    HotelDTO hotel;
    FlightDTO flight;

    HotelsRepository hotelsRepository = Mockito.mock(HotelsRepository.class);
    FlightsRepository flightsRepository = Mockito.mock(FlightsRepository.class);

    Validations validations = new Validations(hotelsRepository);
    Validations flightValidations = new Validations(flightsRepository);

    @BeforeEach
    void setUp() throws IOException {
        hotel = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/hotelForTest.json"),
                new TypeReference<HotelDTO>() {
                });
        flight = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/FlightForTest.json"),
                new TypeReference<FlightDTO>() {
                });
    }

    @Test
    @DisplayName("Date validation true")
    void validateDateTrue() {
        String date = "01/02/1997";
        assertEquals(true, validations.validateDate(date));
    }

    @Test
    @DisplayName("Date validation false")
    void validateDateFalse() {
        String date = "01/02/19997";
        String date2 = "01/002/1997";
        String date3 = "001/02/1997";
        assertEquals(false, validations.validateDate(date));
        assertEquals(false, validations.validateDate(date2));
        assertEquals(false, validations.validateDate(date3));
    }

    @Test
    @DisplayName("Email validation test")
    void validateEmail() throws ApiException {
        String emailForTest = "josediaz4297@gmail.com";
        String badEmailForTest = "josegmail.com";
        assertDoesNotThrow(() -> validations.validateEmail(emailForTest));
        assertThrows(ApiException.class, () -> validations.validateEmail(badEmailForTest));
    }

    @Test
    @DisplayName("Hotel room validation test")
    void validateHotelRoom() throws IOException {
        String hotelRoomForTest = "Single";
        String badHotelRoomForTest = "Doblx";
        assertDoesNotThrow(() -> validations.validateHotelRoom(hotel, hotelRoomForTest));
        assertThrows(ApiException.class, () -> validations.validateHotelRoom(hotel, badHotelRoomForTest));
        assertThrows(ApiException.class, () -> validations.validateHotelRoom(hotel, null));
    }

    @Test
    @DisplayName("Destination validation test")
    void validateDestination() {
        String destinationForTest = "Buenos Aires";
        String badDestinationForTest = "BBBBBuenos";
        String destination = "Buenos Aires";

        assertDoesNotThrow(() -> validations.validateDestination(destinationForTest, destination));
        assertThrows(ApiException.class, () -> validations.validateDestination(badDestinationForTest, destination));
        assertThrows(NullPointerException.class, () -> validations.validateDestination(badDestinationForTest, null));
    }

    @Test
    @DisplayName("Origin validation test")
    void validOrigin() {
        String originForTest = "Buenos Aires";
        String badOriginForTest = "BBBBBuenos";
        String origin = "Buenos Aires";

        assertDoesNotThrow(() -> validations.validOrigin(originForTest, origin));
        assertThrows(ApiException.class, () -> validations.validOrigin(badOriginForTest, origin));
        assertThrows(NullPointerException.class, () -> validations.validOrigin(badOriginForTest, null));
    }

    @Test
    @DisplayName("Date from validation test")
    void validateDateFrom() {
        String dateForTest = "10/02/2021";
        String badHotelDateForTest = "11/02/2021";
        String badDateForTest = "01/002/2021";

        assertDoesNotThrow(() -> validations.validateDateFrom(dateForTest, hotel));
        assertThrows(ApiException.class, () -> validations.validateDateFrom(badHotelDateForTest, hotel));
        assertThrows(ApiException.class, () -> validations.validateDateFrom(badDateForTest, hotel));
    }

    @Test
    @DisplayName("Date to validation test")
    void validateDateTo() {
        String dateForTest = "19/03/2021";
        String badHotelDateForTest = "20/03/2021";
        String badDateForTest = "01/002/2021";

        assertDoesNotThrow(() -> validations.validateDateTo(dateForTest, hotel));
        assertThrows(ApiException.class, () -> validations.validateDateTo(badHotelDateForTest, hotel));
        assertThrows(ApiException.class, () -> validations.validateDateTo(badDateForTest, hotel));
    }

    @Test
    @DisplayName("Seat Type validation test")
    void validateSeatType() {
        String seatForTest = "Economy";
        String badSeatForTest = "Royal";
        String validSeat = "Economy";

        assertDoesNotThrow(() -> validations.validateSeatType(seatForTest, validSeat));
        assertThrows(ApiException.class, () -> validations.validateSeatType(badSeatForTest, validSeat));
    }

    @Test
    @DisplayName("Room tipe & Number of people validations tests")
    void validateRoomTypeAndPeople() {
        assertDoesNotThrow(() -> validations.validateRoomTypeAndPeople("Single", 1));
        assertDoesNotThrow(() -> validations.validateRoomTypeAndPeople("Doble", 2));
        assertDoesNotThrow(() -> validations.validateRoomTypeAndPeople("Triple", 3));
        assertDoesNotThrow(() -> validations.validateRoomTypeAndPeople("Multiple", 8));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Single", null));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Single", 11));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople(null, 10));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("", 10));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Single", 10));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Doble", 10));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Triple", 10));
        assertThrows(ApiException.class, () -> validations.validateRoomTypeAndPeople("Multiple", 11));

    }

    @Test
    @DisplayName("Booking general validations tests")
    void validateBooking() throws IOException {
        PayloadHotelBookingDTO nullBooking = new PayloadHotelBookingDTO();

        PayloadHotelBookingDTO nullHotelCode = new PayloadHotelBookingDTO();
        nullHotelCode.setBooking(new BookingDTO());

        PayloadHotelBookingDTO emptyHotelCode = new PayloadHotelBookingDTO();
        emptyHotelCode.setBooking(new BookingDTO());
        emptyHotelCode.getBooking().setHotelCode("");

        PayloadHotelBookingDTO hotelCodeNotMatch = new PayloadHotelBookingDTO();
        emptyHotelCode.setBooking(new BookingDTO());
        emptyHotelCode.getBooking().setHotelCode("CF-1598");

        PayloadHotelBookingDTO payload = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/PayloadForTest.json"),
                new TypeReference<PayloadHotelBookingDTO>() {
                });
        List<HotelDTO> dbForTest = objectMapper.readValue(
                new File("src/test/resources/dbHotels.json"),
                new TypeReference<List<HotelDTO>>() {
                });
        when(hotelsRepository.getAll()).thenReturn(dbForTest);

        PayloadHotelBookingDTO hotelForTest = new PayloadHotelBookingDTO();
        emptyHotelCode.setBooking(new BookingDTO());
        emptyHotelCode.getBooking().setHotelCode("CF-0002");

        assertThrows(ApiException.class, () -> validations.validateBooking(nullBooking));
        assertThrows(ApiException.class, () -> validations.validateBooking(nullHotelCode));
        assertThrows(ApiException.class, () -> validations.validateBooking(emptyHotelCode));
        assertThrows(ApiException.class, () -> validations.validateBooking(hotelCodeNotMatch));
        assertDoesNotThrow(() -> validations.validateBooking(payload));

    }

    @Test
    @DisplayName("Flight reservation general validations tests")
    void validateFlightReservation() throws IOException {
        PayloadFlightReservDTO nullFlightReserve = new PayloadFlightReservDTO();

        PayloadFlightReservDTO nullFlightNumber = new PayloadFlightReservDTO();
        nullFlightReserve.setFlightReservation(new FlightReservationDTO());

        PayloadFlightReservDTO emptyFlightNumber = new PayloadFlightReservDTO();
        emptyFlightNumber.setFlightReservation(new FlightReservationDTO());
        emptyFlightNumber.getFlightReservation().setFlightNumber("");

        PayloadFlightReservDTO flightNumberNotMatch = new PayloadFlightReservDTO();
        flightNumberNotMatch.setFlightReservation(new FlightReservationDTO());
        flightNumberNotMatch.getFlightReservation().setFlightNumber("CF-1598");

        PayloadFlightReservDTO payload = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/PayloadFlightTest.json"),
                new TypeReference<PayloadFlightReservDTO>() {
                });
        List<FlightDTO> dbForTest = objectMapper.readValue(
                new File("src/test/resources/ValidationsTest/dbFlights.json"),
                new TypeReference<List<FlightDTO>>() {
                });
        when(flightsRepository.getAll()).thenReturn(dbForTest);

        PayloadFlightReservDTO flightForTest = new PayloadFlightReservDTO();
        flightForTest.setFlightReservation(new FlightReservationDTO());
        flightForTest.getFlightReservation().setFlightNumber("BAPI-1235");

        assertThrows(ApiException.class, () -> flightValidations.validateFlightReservation(nullFlightReserve));
        assertThrows(ApiException.class, () -> flightValidations.validateFlightReservation(nullFlightNumber));
        assertThrows(ApiException.class, () -> flightValidations.validateFlightReservation(emptyFlightNumber));
        assertThrows(ApiException.class, () -> flightValidations.validateFlightReservation(flightNumberNotMatch));
        assertDoesNotThrow(() -> flightValidations.validateFlightReservation(payload));
    }
}