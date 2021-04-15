package co.meli.qaproject.dto;

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
    Integer price;
    Date dateFrom;
    Date dateTo;
}
