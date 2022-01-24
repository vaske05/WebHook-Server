package com.elfak.whserver.facade.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirQualitySelectCountriesResponse {
    private List<String> countries;
}
