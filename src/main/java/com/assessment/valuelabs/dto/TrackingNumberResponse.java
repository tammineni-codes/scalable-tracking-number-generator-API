package com.assessment.valuelabs.dto;

import java.time.OffsetDateTime;

public record TrackingNumberResponse(String tracking_number, OffsetDateTime created_at) {}

