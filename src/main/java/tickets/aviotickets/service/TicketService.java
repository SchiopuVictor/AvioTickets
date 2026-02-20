package tickets.aviotickets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tickets.aviotickets.dtos.TickestResponseDto;
import tickets.aviotickets.dtos.TicketRequestDto;
import tickets.aviotickets.entity.Flight;
import tickets.aviotickets.entity.Passenger;
import tickets.aviotickets.entity.Ticket;
import tickets.aviotickets.mapper.TickestMapper;
import tickets.aviotickets.repository.FlightRepository;
import tickets.aviotickets.repository.PassengerRepository;
import tickets.aviotickets.repository.TicketRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final TicketsPDFService ticketsPDFService;

    @Transactional
    public Ticket createTicket(TicketRequestDto request) {
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(
                () -> new RuntimeException("flight not found"));

        Passenger passenger = passengerRepository.findById(request.getPassengerId()).orElseThrow(
                ()-> new RuntimeException("passenger not found"));

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setBookingDate(LocalDateTime.now());
        ticket.setPrice(flight.getBasePrice());
        ticket.setClassName(request.getClassName());
        ticket.setSeatNumber(request.getSeatNumber());

        ticketRepository.save(ticket);
        return ticket;
    }

    public TickestResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("ticket not found"));

        return TickestMapper.toDto(ticket);
    }

    @Transactional
    public void deleteTicket(Long id) {
        ticketRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("ticket not found"));
        ticketRepository.deleteById(id);
    }

    public byte[] getTicketPdf(long id) throws Exception {
       Ticket ticket = ticketRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Ticket not found"));
        return ticketsPDFService.createPdf(ticket);
    }

}
