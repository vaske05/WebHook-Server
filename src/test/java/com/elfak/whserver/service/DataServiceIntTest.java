package com.elfak.whserver.service;


import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.dto.*;
import com.elfak.whserver.model.dto.vendor.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.vendor.CovidResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataServiceIntTest extends IntegrationTestPrototype {

    @Autowired
    DataService dataService;

    @Test
    public void testGetCovidData() throws Exception {
        // Given
        CovidRequestDTO covidRequestDTO1 = new CovidRequestDTO();
        CovidRequestDTO covidRequestDTO2 = new CovidRequestDTO();
        covidRequestDTO1.setCountry("Serbia");
        covidRequestDTO2.setCountry("Austria");
        // When
        CovidResponseDTO responseDTO1 = dataService.getCovidData(covidRequestDTO1);
        CovidResponseDTO responseDTO2 = dataService.getCovidData(covidRequestDTO2);
        // Then
        Assert.assertEquals("Serbia", responseDTO1.getCountry());
        Assert.assertEquals("Austria", responseDTO2.getCountry());
    }

    @Test
    public void testGetCovidDataThrowException() {
        Assert.assertThrows(Exception.class, () -> dataService.getCovidData(new CovidRequestDTO("noCountry")));
    }

    @Test
    public void testGetAirQualityData() throws Exception {
        // Given
        AirQualityRequestDTO requestDTO = new AirQualityRequestDTO("Serbia", "Central Serbia", "Nis");
        // When
        AirQualityResponseDTO airQualityResponseDTO = dataService.getAirQualityData(requestDTO);
        // Then
        Assert.assertEquals("Serbia", airQualityResponseDTO.getData().getCountry());
        Assert.assertEquals("Nis", airQualityResponseDTO.getData().getCity());
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
