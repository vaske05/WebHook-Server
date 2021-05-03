package com.elfak.whserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirQualityRequestDTO {
    String country;
    String state;
    String city;
}
