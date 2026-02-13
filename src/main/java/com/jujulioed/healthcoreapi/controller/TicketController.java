package com.jujulioed.healthcoreapi.controller;

import com.jujulioed.healthcoreapi.dto.response.*;
import com.jujulioed.healthcoreapi.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.DateFormat;

@RestController
@RequestMapping("/user/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> generateTicket(@RequestBody String userId) {
        TicketResponseDTO ticketResponseDTO = ticketService.generateATicket(userId);
        return ResponseEntity.created(URI.create("")).body(ticketResponseDTO);
    }

    // SUGGESTION
    @GetMapping
    public String checkTickeStatus(@RequestBody String ticketId) {
        // Request for the broker to provide the ticket status
        return "Id: " + ticketId + ", Ticket Status: Valid";
    }

    @GetMapping("/occupancy")
    public String currentOccupancy(@RequestBody String region) {
        // Request for the broker to provide the occupancy of the nearby hospitals
        return "EXAMPLE: Unit1: 70%, Unit2: 50%";
    }
}
