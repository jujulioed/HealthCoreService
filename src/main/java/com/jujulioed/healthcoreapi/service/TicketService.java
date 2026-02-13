package com.jujulioed.healthcoreapi.service;

import com.jujulioed.healthcoreapi.dto.response.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

@Service
public class TicketService {

    public TicketResponseDTO generateATicket(String userId) {
        // Request for the broker to provide the ticket number
        // It needs to validate also if the user already has a ticket number (the user can't have two valid tickets)

        EstimatedServiceDTO estimatedServiceDTO = new EstimatedServiceDTO("10", "15", "5", "45");
        TicketNumberDTO ticketNumberDTO = new TicketNumberDTO("456", "CONSULTA", new Date());
        UnitDTO unitDTO = new UnitDTO("UBS-312", "UBS Centro");
        UserDTO userDTO = new UserDTO("Jorge Naodo");
        DataDTO dataDTO = new DataDTO(userDTO, unitDTO, ticketNumberDTO, estimatedServiceDTO);
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO("OK",
                "Senha gerada com sucesso",
                dataDTO);

        return ticketResponseDTO;
    }
}
