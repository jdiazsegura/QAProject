package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.ResponseFlightReservDTO;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.dto.hotels.ResponseHotelBookDTO;
import co.meli.qaproject.exceptions.ApiException;
import co.meli.qaproject.exceptions.IncorrectFormatException;
import co.meli.qaproject.exceptions.NoValidDatesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IMainController {


    @GetMapping("/hotels")
    ResponseEntity<List<HotelDTO>> getHotels(@RequestParam Map<String, String> allParams) throws IncorrectFormatException, NoValidDatesException;

    @PostMapping("/booking")
    ResponseEntity<ResponseHotelBookDTO> bookingHotel(@RequestBody PayloadHotelBookingDTO payloadHotelBook) throws ApiException;

    @GetMapping("/flights")
    ResponseEntity<List<FlightDTO>> getFlights(@RequestParam Map<String, String> allParams);

    @PostMapping("/flight-reservation")
    ResponseEntity<ResponseFlightReservDTO> reserveFlight(@RequestBody co.meli.qaproject.dto.flights.PayloadFlightReservDTO payloadFlightReserv);
}
