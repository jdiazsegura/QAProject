package co.meli.qaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightNormDTO {
    String dateFrom;
    String dateTo;
    String origin;
    String destination;
}
