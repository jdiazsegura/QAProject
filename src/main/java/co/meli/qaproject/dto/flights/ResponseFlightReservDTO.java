package co.meli.qaproject.dto.flights;

import co.meli.qaproject.dto.StatusCodeDTO;
import co.meli.qaproject.dto.hotels.PayloadHotelBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFlightReservDTO {
    String username;
    Double amount;
    Double interest;
    Double total;
    FlightReservationDTO flightReservation;
    StatusCodeDTO statusCode;

    public ResponseFlightReservDTO(PayloadFlightReservDTO payloadFlightReservDTO) {
        this.username = payloadFlightReservDTO.getUserName();
        this.flightReservation = payloadFlightReservDTO.getFlightReservation();
    }
}
