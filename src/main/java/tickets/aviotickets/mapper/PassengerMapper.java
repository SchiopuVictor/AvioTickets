package tickets.aviotickets.mapper;

import tickets.aviotickets.dtos.PassengerDto;
import tickets.aviotickets.entity.Passenger;

public class PassengerMapper {
    public static PassengerDto toDto(Passenger request) {
        return PassengerDto.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    public static Passenger toEntity(PassengerDto request) {
        return Passenger.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

}
