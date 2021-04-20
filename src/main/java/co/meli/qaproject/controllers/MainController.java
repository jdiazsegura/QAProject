package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.*;
import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.ResponseFlightReservDTO;
import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.dto.hotels.ResponseHotelBookDTO;
import co.meli.qaproject.exceptions.ApiException;
import co.meli.qaproject.exceptions.IncorrectFormatException;
import co.meli.qaproject.exceptions.NoValidDatesException;
import co.meli.qaproject.services.FlightsService;
import co.meli.qaproject.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class MainController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private FlightsService flightsService;

    //Miss documentation & fixing POST Methods tests


    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> getHotels(@RequestParam Map<String,String> allParams) throws IncorrectFormatException, NoValidDatesException {
        if (allParams.isEmpty()){
            return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(hotelService.getAllByDateAndCity(allParams), HttpStatus.OK);
        }
    }


    @PostMapping("/booking")
    public ResponseEntity<ResponseHotelBookDTO> bookingHotel(@RequestBody PayloadHotelBookingDTO payloadHotelBook) throws ApiException {
        return new ResponseEntity<>(hotelService.bookHotel(payloadHotelBook), HttpStatus.OK);
    }

    // FLIGHTS ENDPOINTS

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDTO>> getFlights(@RequestParam Map<String,String> allParams){
        if (allParams.isEmpty()){
            return new ResponseEntity<>(flightsService.getAll(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(flightsService.getAvailableFlights(allParams),HttpStatus.OK);
        }
    }

    @PostMapping("/flight-reservation")
    public ResponseEntity<ResponseFlightReservDTO> reserveFlight(@RequestBody co.meli.qaproject.dto.flights.PayloadFlightReservDTO payloadFlightReserv){
        return new ResponseEntity<>(flightsService.reserveFlight(payloadFlightReserv),HttpStatus.OK);
    }




    // EXCEPTION HANDLERS
    @ExceptionHandler(IncorrectFormatException.class)
    public ResponseEntity<StatusCodeDTO> incorrectFormatExceptionHandler(IncorrectFormatException incorrectFormatException){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(400);
        statusCodeDTO.setMessage("Incorrect Date: " + incorrectFormatException.getMessage());
        return new ResponseEntity<>(statusCodeDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoValidDatesException.class)
    public ResponseEntity<StatusCodeDTO> noValidDatesExceptionHandler(NoValidDatesException noValidDatesException){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(400);
        statusCodeDTO.setMessage("Incorrect Date: " + noValidDatesException.getMessage());
        return new ResponseEntity<>(statusCodeDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StatusCodeDTO> ApiExceptionHandler(ApiException apiException){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(400);
        statusCodeDTO.setMessage(apiException.getMessage());
        return new ResponseEntity<>(statusCodeDTO,HttpStatus.BAD_REQUEST);
    }
}
