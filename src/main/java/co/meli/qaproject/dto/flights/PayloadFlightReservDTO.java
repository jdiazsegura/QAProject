package co.meli.qaproject.dto.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadFlightReservDTO {

    String userName;
    FlightReservationDTO flightReservation;

}
