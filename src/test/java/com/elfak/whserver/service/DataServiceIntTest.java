package com.elfak.whserver.service;


import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.service.dto.airQuality.*;
import com.elfak.whserver.service.dto.covid.CovidDataRequestDTO;
import com.elfak.whserver.service.dto.covid.CovidDataResponseDTO;
import com.elfak.whserver.service.dto.covid.CovidSelectCountriesResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataServiceIntTest extends IntegrationTestPrototype {

    @Autowired
    DataService dataService;

    @Test
    public void testGetCovidData() throws Exception {
        // Given
        CovidDataRequestDTO covidDataRequestDTO1 = new CovidDataRequestDTO();
        CovidDataRequestDTO covidDataRequestDTO2 = new CovidDataRequestDTO();
        covidDataRequestDTO1.setCountry("Serbia");
        covidDataRequestDTO2.setCountry("Austria");
        // When
        CovidDataResponseDTO responseDTO1 = dataService.getCovidData(covidDataRequestDTO1);
        CovidDataResponseDTO responseDTO2 = dataService.getCovidData(covidDataRequestDTO2);
        // Then
        Assert.assertEquals("Serbia", responseDTO1.getCountry());
        Assert.assertEquals("Austria", responseDTO2.getCountry());
    }

    @Test
    public void testGetCovidDataThrowException() {
        Assert.assertThrows(Exception.class, () -> dataService.getCovidData(new CovidDataRequestDTO("noCountry")));
    }

    @Test
    public void testGetAirQualityData() throws Exception {
        // Given
        AirQualityDataRequestDTO requestDTO = new AirQualityDataRequestDTO("Serbia", "Central Serbia", "Nis");
        // When
        AirQualityDataResponseDTO airQualityDataResponseDTO = dataService.getAirQualityData(requestDTO);
        // Then
        Assert.assertEquals("Serbia", airQualityDataResponseDTO.getData().getCountry());
        Assert.assertEquals("Nis", airQualityDataResponseDTO.getData().getCity());
    }

    @Test
    public void testGetCovidSelectCountries() {
        // When
        CovidSelectCountriesResponseDTO covidSelectCountriesResponseDTO = dataService.getCovidSelectCountries();
        // Then
        Assert.assertFalse(covidSelectCountriesResponseDTO.getCountries().isEmpty());
    }

    @Test
    public void testGetAirQualitySelectCountries() {
        // When
        AirQualitySelectCountriesResponseDTO airSelectCountriesDto = dataService.getAirSelectCountries();
        // Then
        Assert.assertFalse(airSelectCountriesDto.getCountries().isEmpty());
    }

    @Test
    public void testGetAirQualitySelectRegions() {
        // Given, When
        AirQualitySelectRegionsResponseDTO airSelectRegionsDto = dataService.getAirSelectRegions("Serbia");
        // Then
        Assert.assertFalse(airSelectRegionsDto.getRegions().isEmpty());
    }

    @Test
    public void testGetAirQualitySelectCities() {
        // Given, When
        AirQualitySelectCitiesResponseDTO airSelectCitiesDto = dataService.getAirSelectCities("Serbia", "Central Serbia");
        // Then
        Assert.assertFalse(airSelectCitiesDto.getCities().isEmpty());
    }

    @Test
    public void testGetAirQualitySelectCitiesNoData() {
        // Given, When
        AirQualitySelectCitiesResponseDTO airSelectCitiesDto = dataService.getAirSelectCities("Andorra", "Encamp");
        // Then
        Assert.assertTrue(airSelectCitiesDto.getCities().isEmpty());
    }
}
