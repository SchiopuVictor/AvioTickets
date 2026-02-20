package tickets.aviotickets.mapper;

import tickets.aviotickets.dtos.FlightDto;
import tickets.aviotickets.entity.Flight;

public class FlightMapper {
    public static FlightDto toDto(Flight request) {
        return FlightDto.builder()
                .flightNumber(request.getFlightNumber())
                .fromCity(request.getFromCity())
                .toCity(request.getToCity())
                .departureTime(request.getDepartureTime())
                .gate(request.getGate())
                .basePrice(request.getBasePrice())
                .terminal(request.getTerminal())
                .build();
    }
    public static Flight toEntity(FlightDto request) {
        return Flight.builder()
                .flightNumber(request.getFlightNumber())
                .fromCity(request.getFromCity())
                .toCity(request.getToCity())
                .basePrice(request.getBasePrice())
                .departureTime(request.getDepartureTime())
                .gate(request.getGate())
                .terminal(request.getTerminal())
                .build();
    }

}
