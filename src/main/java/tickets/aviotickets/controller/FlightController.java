package tickets.aviotickets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tickets.aviotickets.dtos.FlightDto;
import tickets.aviotickets.entity.Flight;
import tickets.aviotickets.mapper.FlightMapper;
import tickets.aviotickets.service.FlightService;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightDto> getFlights(@PathVariable long id) {
        return ResponseEntity.ok().body(flightService.getFlight(id));
    }

    @PostMapping("/flights")
    public ResponseEntity<FlightDto> createFlights(@Valid @RequestBody FlightDto request) {
        Flight flight = flightService.createFlight(request);
       return ResponseEntity.ok(FlightMapper.toDto(flight));
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<FlightDto> updateFlights(@Valid @RequestBody FlightDto request
            , @PathVariable long id) {
        Flight flight = flightService.updateFlight(request,id);
        return ResponseEntity.ok(FlightMapper.toDto(flight));

    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlights(@PathVariable long id) {
        flightService.deleteFlight(id);
    }




}
