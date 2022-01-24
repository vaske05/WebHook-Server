package com.elfak.whserver.facade.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirQualitySelectDataRequest {
    private String country;
    private String region;
}
