package com.elfak.whserver.facade.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirQualitySelectRegionsResponse {
    private List<String> regions;
}
