package com.elfak.whserver.service.dto.airQuality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityCitiesResponseRaw {

    private String status;
    private List<Data> data;

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        public String city;

    }
}
