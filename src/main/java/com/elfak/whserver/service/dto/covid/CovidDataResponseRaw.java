package com.elfak.whserver.service.dto.covid;

import lombok.Data;

import java.util.Date;

@Data
public class CovidDataResponseRaw {
    Date updated;
    String country;
    int cases;
    int todayCases;
    int deaths;
    int todayDeaths;
    int recovered;
    int todayRecovered;
    int active;
    int critical;
    int casesPerOneMillion;
    int deathsPerOneMillion;
    int tests;
    int testsPerOneMillion;
    int population;
    String continent;
    int oneCasePerPeople;
    int oneDeathPerPeople;
    int oneTestPerPeople;
    int activePerOneMillion;
    int recoveredPerOneMillion;
    int criticalPerOneMillion;
}
