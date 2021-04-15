package co.meli.qaproject.controllers;

import co.meli.qaproject.dto.HotelDTO;
import co.meli.qaproject.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> get(){
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
    }

}
