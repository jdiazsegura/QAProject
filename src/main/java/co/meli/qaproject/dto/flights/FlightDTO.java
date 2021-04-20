package co.meli.qaproject.dto.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    String flightNumber;
    String origin;
    String destination;
    String seatType;
    Integer price;
    String dateFrom;
    String dateTo;
}
