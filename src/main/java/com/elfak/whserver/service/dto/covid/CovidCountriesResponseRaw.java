package com.elfak.whserver.service.dto.covid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CovidCountriesResponseRaw {
    private String country;
}
