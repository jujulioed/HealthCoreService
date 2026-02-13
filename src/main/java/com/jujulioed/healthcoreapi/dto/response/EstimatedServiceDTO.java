package com.jujulioed.healthcoreapi.dto.response;

public record EstimatedServiceDTO(String averageTimeInMinutes,
                                  String patientsInQueue,
                                  String availableDoctors,
                                  String estimatedTimeInMinutes) {
}
