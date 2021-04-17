package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.dto.PayloadHotelBookDTO;
import co.meli.qaproject.dto.ResponseHotelBookDTO;
import co.meli.qaproject.exceptions.IncorrectFormatException;
import co.meli.qaproject.exceptions.NoValidDatesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMainController {
    @GetMapping("/hotels")
    ResponseEntity<List<HotelDTO>> get(@RequestParam(required = false) String dateTo, @RequestParam(required = false) String dateFor, @RequestParam(required = false) String destination) throws IncorrectFormatException, NoValidDatesException;

    @PostMapping("/booking")
    ResponseEntity<ResponseHotelBookDTO> bookingHotel(@RequestBody PayloadHotelBookDTO payloadHotelBook);
}
