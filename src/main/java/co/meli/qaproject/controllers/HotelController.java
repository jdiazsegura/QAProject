package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> get(@RequestParam(required = false) String dateTo,@RequestParam(required = false) String dateFor,@RequestParam(required = false) String destination){
        if (dateTo == null && dateFor == null && destination == null){
            return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(hotelService.getAllByDateAndCity(dateTo, dateFor, destination), HttpStatus.OK);
        }
    }

}
