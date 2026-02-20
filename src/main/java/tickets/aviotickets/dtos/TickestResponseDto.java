package tickets.aviotickets.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import tickets.aviotickets.entity.ClassName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TickestResponseDto {
    private String seatNumber;
    private BigDecimal price;
    private ClassName className;
    private LocalDateTime bookingDate;
    private String passengerId;
    private String flightId;

}
