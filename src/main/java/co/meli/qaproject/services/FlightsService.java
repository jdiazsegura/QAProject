package co.meli.qaproject.services;

import co.meli.qaproject.dto.FlightDTO;
import co.meli.qaproject.dto.FlightNormDTO;

import java.util.List;
import java.util.Map;

public interface FlightsService {
    List<FlightDTO> getAll();

    List<FlightDTO> getAvailableFlights(Map<String, String> allParams);

    FlightNormDTO flightNorm(Map<String, String> allParams);
}
