package com.elfak.whserver.service.dto.covid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CovidSelectCountriesResponseDTO {
    private List<String> countries;
}
