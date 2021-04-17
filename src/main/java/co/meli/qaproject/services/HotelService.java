package co.meli.qaproject.services;

import co.meli.qaproject.dto.*;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getAll();

    List<HotelDTO> getAllByDateAndCity(String dateTo, String dateFor, String city);

    ResponseHotelBookDTO bookHotel(PayloadHotelBookDTO payloadHotelBook);

    Integer calculateNights(String DateTo, String DateFrom);

    Double priceByNights(Integer numNights, Double nightValue);

    Double valueOfInterest(String type, Integer dues);

    Double getTotalValue(Double priceByNights, Double valueOfInterest);
}
