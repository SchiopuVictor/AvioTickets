package tickets.aviotickets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tickets.aviotickets.dtos.PassengerDto;
import tickets.aviotickets.entity.Passenger;
import tickets.aviotickets.mapper.PassengerMapper;
import tickets.aviotickets.service.PassengerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping("/passengers/{id}")
    public ResponseEntity<PassengerDto> getPassenger(@PathVariable long id){
        return ResponseEntity.ok(passengerService.getPassenger(id));
    }

    @PostMapping("/passengers")
    public ResponseEntity<PassengerDto> createPassenger(
            @Valid @RequestBody PassengerDto passengerDto){
        Passenger passenger = passengerService.createPassenger(passengerDto);
        return ResponseEntity.ok(PassengerMapper.toDto(passenger));
    }

    @PutMapping("/passengers/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(
            @Valid @RequestBody PassengerDto request,
            @PathVariable long id){
        Passenger passenger = passengerService.updatePassenger(id, request);
        return ResponseEntity.ok(PassengerMapper.toDto(passenger));
    }

    @DeleteMapping("/passengers/{id}")
    public void deletePassenger(@PathVariable long id){
        passengerService.deletePassenger(id);
    }


}
