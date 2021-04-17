package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.dto.PayloadHotelBookDTO;
import co.meli.qaproject.dto.ResponseHotelBookDTO;
import co.meli.qaproject.dto.StatusCodeDTO;
import co.meli.qaproject.exceptions.IncorrectFormatException;
import co.meli.qaproject.exceptions.NoValidDatesException;
import co.meli.qaproject.services.HotelService;
import co.meli.qaproject.utils.Validations;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    private Validations validations = new Validations();

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> get(@RequestParam(required = false) String dateTo,@RequestParam(required = false) String dateFor,@RequestParam(required = false) String destination) throws IncorrectFormatException, NoValidDatesException {
        if (dateTo == null && dateFor == null && destination == null){
            return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(hotelService.getAllByDateAndCity(dateTo, dateFor, destination), HttpStatus.OK);
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<ResponseHotelBookDTO> bookingHotel(@RequestBody PayloadHotelBookDTO payloadHotelBook){
        return new ResponseEntity<>(hotelService.bookHotel(payloadHotelBook), HttpStatus.OK);
    }


    // EXCEPTION HANDLERS
    @ExceptionHandler(IncorrectFormatException.class)
    public ResponseEntity<StatusCodeDTO> incorrectFormatExceptionHandler(IncorrectFormatException incorrectFormatException){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(404);
        statusCodeDTO.setMessage("Incorrect Date: " + incorrectFormatException.getMessage());
        return new ResponseEntity<>(statusCodeDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoValidDatesException.class)
    public ResponseEntity<StatusCodeDTO> noValidDatesExceptionHandler(NoValidDatesException noValidDatesException){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(404);
        statusCodeDTO.setMessage("Incorrect Date: " + noValidDatesException.getMessage());
        return new ResponseEntity<>(statusCodeDTO,HttpStatus.NOT_FOUND);
    }
}
