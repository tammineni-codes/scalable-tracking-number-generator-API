package com.assessment.valuelabs.controller;

import com.assessment.valuelabs.dto.TrackingNumberResponse;
import com.assessment.valuelabs.service.TrackingNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
public class TrackingNumberController {

    private final TrackingNumberService trackingNumberService;

    @GetMapping("/next-tracking-number")
    public ResponseEntity<TrackingNumberResponse> getTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam BigDecimal weight,
            @RequestParam OffsetDateTime created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ) {
        String trackingNumber = trackingNumberService.generateTrackingNumber(
                origin_country_id, destination_country_id, weight, created_at, customer_id, customer_name, customer_slug
        );

        return ResponseEntity.ok(new TrackingNumberResponse(trackingNumber, OffsetDateTime.now()));
    }
}

