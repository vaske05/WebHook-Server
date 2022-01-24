package com.elfak.whserver.facade.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CovidSelectCountriesResponse {
    private List<String> countries;
}
