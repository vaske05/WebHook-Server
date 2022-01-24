package com.elfak.whserver.model.dto.vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityResponseDTO { // TODO: Create Raw of this

    private String status;
    private Data data;

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        public String city;
        public String state;
        public String country;

        private Current current;
    }

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Current {
        private Weather weather;
        private Pollution pollution;
    }

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather {
        private LocalDateTime ts;
        private Integer tp;
        private Integer pr;
        private Integer hu;
        private Double ws;
        private Integer wd;
        private String ic;

    }

    @lombok.Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pollution {
        private LocalDateTime ts;
        private Integer aqius;
        private String mainus;
        private Integer aqicn;
        private String maincn;
    }
}
