package co.meli.qaproject.dto.flights;

import co.meli.qaproject.dto.PaymentMethodDTO;
import co.meli.qaproject.dto.PeopleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationDTO {

    String dateFrom;
    String dateTo;
    String origin;
    String destination;
    String flightNumber;
    Integer seats;
    String seatType;
    List<PeopleDTO> people;
    PaymentMethodDTO paymentMethod;

}
