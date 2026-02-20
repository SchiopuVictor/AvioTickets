package tickets.aviotickets.mapper;

import tickets.aviotickets.dtos.TickestResponseDto;
import tickets.aviotickets.entity.Ticket;

public class TickestMapper {

    public static TickestResponseDto toDto(Ticket request) {
        return TickestResponseDto.builder()
                .seatNumber(request.getSeatNumber())
                .price(request.getPrice())
                .className(request.getClassName())
                .bookingDate(request.getBookingDate())
                .passengerId(request.getPassenger().getFirstName() + " "+
                        request.getPassenger().getLastName())
                .flightId(request.getFlight().getFlightNumber())
                .build();
    }

    public static Ticket toEntity(TickestResponseDto request) {
        return Ticket.builder()
                .seatNumber(request.getSeatNumber())
                .className(request.getClassName())
                .price(request.getPrice())
                .bookingDate(request.getBookingDate())
                .build();
    }
}
