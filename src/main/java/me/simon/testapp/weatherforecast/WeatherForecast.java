package me.simon.testapp.weatherforecast;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * @author Simon Brebeck on 04/07/2023
 */
@Entity
@Table(name = "weather_forecasts")
@Getter
@Setter
@RequiredArgsConstructor
public class WeatherForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_date_time")
    @NotNull
    private Date date;

    @Column(name = "city_name")
    @NotNull
    String city;

    @Column(name = "city_postcode")
    @NotNull
    String postcode;

    @Column(name = "country_name")
    @NotNull
    String country;

    @Column(name = "weather_description", length = 1000)
    @NotNull
    @Max(1000)
    String weatherDescription;

    @Column(name = "temperature")
    @NotNull
    Double temperature;

    @Column(name = "wind_speed")
    @NotNull
    Double windSpeed;

    @Column(name = "wind_direction")
    @NotNull
    Double windDirection;

    @Column(name = "air_pressure")
    @NotNull
    Double airPressure;

    @Column(name = "humidity")
    @NotNull
    Double humidity;

    @Column(name = "rainfall_probability")
    @NotNull
    Double rainfallProbability;

}
