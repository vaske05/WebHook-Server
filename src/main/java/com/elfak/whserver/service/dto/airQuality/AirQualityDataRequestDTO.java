package com.elfak.whserver.service.dto.airQuality;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirQualityDataRequestDTO {
    String country;
    String state;
    String city;
}
