package me.simon.testapp.weatherforecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * @author Simon Brebeck on 04/07/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastCreationDTO {
    /*
    You can use different DTOs for different purposes, e.g. a WeatherForecastCreationDTO for creating a new WeatherForecast
    It does not matter in this case as the DTOs are identical. But for the sake of completeness, I will add a WeatherForecastCreationDTO
    */
    @JsonProperty
    private Date date;

    @JsonProperty
    String city;

    @JsonProperty
    String postcode;

    @JsonProperty
    String country;

    @JsonProperty
    @Max(1000)
    String weatherDescription;

    @JsonProperty
    Double temperature;

    @JsonProperty
    Double windSpeed;

    @JsonProperty
    Double windDirection;

    @JsonProperty
    Double airPressure;

    @JsonProperty
    Double humidity;

    @JsonProperty
    Double rainfallProbability;

}
