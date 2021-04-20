package co.meli.qaproject.dto.hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadHotelBookingDTO {
    String userName;
    BookingDTO booking;
}
