package tickets.aviotickets.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private String flightNumber;
    private String fromCity;
    private String toCity;
    private LocalDateTime departureTime;
    private String gate;
    private BigDecimal basePrice;
    private String terminal;

}
