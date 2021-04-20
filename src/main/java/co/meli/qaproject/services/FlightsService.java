package co.meli.qaproject.services;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.FlightNormDTO;
import co.meli.qaproject.dto.flights.PayloadFlightReservDTO;
import co.meli.qaproject.dto.flights.ResponseFlightReservDTO;

import java.util.List;
import java.util.Map;

public interface FlightsService {
    List<FlightDTO> getAll();

    List<FlightDTO> getAvailableFlights(Map<String, String> allParams);

    FlightNormDTO flightNorm(Map<String, String> allParams);

    ResponseFlightReservDTO reserveFlight(PayloadFlightReservDTO payloadFlightReserv);

    Double valueOfInterest(String type, Integer dues);

    Double getTotalValue(Double Amount, Double valueOfInterest);
}
