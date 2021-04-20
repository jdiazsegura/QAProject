package co.meli.qaproject.services;

import co.meli.qaproject.dto.hotels.HotelDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import co.meli.qaproject.dto.hotels.ResponseHotelBookDTO;
import co.meli.qaproject.exceptions.ApiException;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<HotelDTO> getAll();


    List<HotelDTO> getAllByDateAndCity(Map<String, String> allParams);

    ResponseHotelBookDTO bookHotel(PayloadHotelBookingDTO payloadHotelBook) throws ApiException;

    Integer calculateNights(String DateTo, String DateFrom);

    Double priceByNights(Integer numNights, Double nightValue);

    Double valueOfInterest(String type, Integer dues);

    Double getTotalValue(Double priceByNights, Double valueOfInterest);
}
