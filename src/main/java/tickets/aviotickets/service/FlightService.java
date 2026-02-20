package tickets.aviotickets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tickets.aviotickets.dtos.FlightDto;
import tickets.aviotickets.entity.Flight;
import tickets.aviotickets.mapper.FlightMapper;
import tickets.aviotickets.repository.FlightRepository;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;


    @Transactional
    public Flight createFlight(FlightDto request){
        Flight flight = FlightMapper.toEntity(request);
        flightRepository.save(flight);
        return flight;
    }

    public FlightDto getFlight(long id){
        Flight flight = flightRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Passenger not found"));
        return FlightMapper.toDto(flight);
    }

    @Transactional
    public Flight updateFlight(FlightDto request, long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Flight not found"));

        flight.setFlightNumber(request.getFlightNumber());
        flight.setFromCity(request.getFromCity());
        flight.setToCity(request.getToCity());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setGate(request.getGate());
        flight.setTerminal(request.getTerminal());
        flightRepository.save(flight);
        return flight;
    }

    @Transactional
    public void deleteFlight(long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Flight not found"));
        flightRepository.delete(flight);
    }

}
