package co.meli.qaproject.dto.hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    String codeHotel;
    String name;
    String city;
    String roomType;
    Integer nightPrice;
    String dateFrom;
    String dateTo;
    Boolean reserved;
}
