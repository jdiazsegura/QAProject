package co.meli.qaproject.services;

import co.meli.qaproject.dto.flights.FlightDTO;
import co.meli.qaproject.dto.flights.FlightNormDTO;
import co.meli.qaproject.dto.flights.PayloadFlightReservDTO;
import co.meli.qaproject.dto.flights.ResponseFlightReservDTO;
import co.meli.qaproject.repositories.FlightsRepository;
import co.meli.qaproject.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlightsServiceImpl implements FlightsService{

    private final FlightsRepository flightsRepository;
    private DateUtils dateUtils = new DateUtils();

    public FlightsServiceImpl(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @Override
    public List<FlightDTO> getAll(){
        return flightsRepository.getAll();
    }

    @Override
    public List<FlightDTO> getAvailableFlights(Map<String,String> allParams){
        var flight = flightNorm(allParams);
        return flightsRepository.getFlightByFilters(
                getAll(), flight.getDateFrom(), flight.getDateTo(), flight.getOrigin(), flight.getDestination());
    }

    @Override
    public FlightNormDTO flightNorm(Map<String,String> allParams){
        return new FlightNormDTO(
                allParams.get("dateFrom")
                ,allParams.get("dateTo")
                ,allParams.get("origin")
                ,allParams.get("destination")
        );
    }

    @Override
    public ResponseFlightReservDTO reserveFlight(PayloadFlightReservDTO payloadFlightReserv){
        ResponseFlightReservDTO payloadResult = new ResponseFlightReservDTO(payloadFlightReserv);
        var reservation = payloadFlightReserv.getFlightReservation();
        var flight = flightsRepository.getFlightByNumber(flightsRepository.getAll(),reservation.getFlightNumber());
        payloadResult.setAmount((double) (flight.getPrice() * reservation.getSeats()));
        payloadResult.setInterest(valueOfInterest(reservation.getPaymentMethod().getType(),reservation.getPaymentMethod().getDues())*10);
        payloadResult.setTotal(getTotalValue(payloadResult.getAmount(), payloadResult.getInterest()));
        return payloadResult;
    }

    @Override
    public Double valueOfInterest(String type, Integer dues) {
        var flag = 0.0;
        if (type.equals("DEBIT")) {
            return flag;
        } else if (type.equals("CREDIT")) {
            if (dues < 3) {
                flag = 0.05;
            } else if (dues <= 6) {
                flag = 0.10;
            }
        }
        return flag;
    }

    @Override
    public Double getTotalValue(Double Amount, Double valueOfInterest){
        return Amount+(Amount*valueOfInterest);
    }


}
