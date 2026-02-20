package tickets.aviotickets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tickets.aviotickets.dtos.TickestResponseDto;
import tickets.aviotickets.dtos.TicketRequestDto;
import tickets.aviotickets.entity.Ticket;
import tickets.aviotickets.mapper.TickestMapper;
import tickets.aviotickets.service.TicketService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/tickets/book")
    public ResponseEntity<TickestResponseDto> creteTicket(@Valid @RequestBody TicketRequestDto ticketRequestDto) {
        Ticket ticket = ticketService.createTicket(ticketRequestDto);
        return ResponseEntity.ok(TickestMapper.toDto(ticket));
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TickestResponseDto>getTicketsById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

    @GetMapping("/tickets/{id}/pdf")
    public ResponseEntity<byte[]> getPdfTicket(@PathVariable Long id) throws Exception {
        byte [] pdf = ticketService.getTicketPdf(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=ticket.pdf")
                .body(pdf);
    }

    @GetMapping("/tickets/{id}/pdf/view")
    public ResponseEntity<byte[]> viewPdfTicket(@PathVariable Long id) throws Exception {
        byte [] pdf = ticketService.getTicketPdf(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline; filename=ticket.pdf")
                .body(pdf);
    }




}
