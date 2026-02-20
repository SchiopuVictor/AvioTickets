package tickets.aviotickets.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tickets.aviotickets.entity.ClassName;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private String seatNumber;
    private ClassName className;
    private long passengerId;
    private long flightId;
}
