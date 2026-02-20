package tickets.aviotickets.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tickets.aviotickets.dtos.PassengerDto;
import tickets.aviotickets.entity.Passenger;
import tickets.aviotickets.mapper.PassengerMapper;
import tickets.aviotickets.repository.PassengerRepository;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerDto  getPassenger(long id){
        Passenger passenger = passengerRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Passenger not found"));
        return PassengerMapper.toDto(passenger);
    }

    @Transactional
    public Passenger createPassenger(PassengerDto passengerDto){
        Passenger passenger = PassengerMapper.toEntity(passengerDto);
        passengerRepository.save(passenger);
        return passenger;
    }

    @Transactional
    public Passenger updatePassenger(long id, PassengerDto request){
        Passenger passenger = passengerRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Passenger not found"));

        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setEmail(request.getEmail());
        passengerRepository.save(passenger);

        return passenger;
    }

    @Transactional
    public void deletePassenger(long id){
        passengerRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Passenger not found"));

        passengerRepository.deleteById(id);
    }

}
