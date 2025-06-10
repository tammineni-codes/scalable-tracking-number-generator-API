package com.assessment.valuelabs.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrackingNumberService {

    private final Set<String> issuedTrackingNumbers = ConcurrentHashMap.newKeySet();

    public String generateTrackingNumber(
            String origin, String destination, BigDecimal weight,
            OffsetDateTime createdAt, UUID customerId, String customerName, String customerSlug
    ) {

        String base = origin + destination + weight + createdAt.toInstant().toEpochMilli() + customerId;
        String hash = DigestUtils.sha256Hex(base).toUpperCase().substring(0, 12);
        String trackingNumber = hash + getRandomAlphaNumeric(4); // 16 chars


        while (!issuedTrackingNumbers.add(trackingNumber)) {
            trackingNumber = hash + getRandomAlphaNumeric(4);
        }

        return trackingNumber;
    }

    private String getRandomAlphaNumeric(int length) {
        return RandomStringUtils.random(length, true, true).toUpperCase();
    }
}

