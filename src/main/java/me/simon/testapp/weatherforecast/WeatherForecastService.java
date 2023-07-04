package me.simon.testapp.weatherforecast;

import org.apache.catalina.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Brebeck on 04/07/2023
 */
public interface WeatherForecastService {

    /**
     * Get all WeatherForecasts between startDate and endDate
     * @param startDate the start date
     * @param endDate the end date
     * @return List of WeatherForecasts
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    List<WeatherForecastDTO> getAll(Date startDate, Date endDate);

    /**
     * Get a single WeatherForecast by id
     * @param id The id of the WeatherForecast
     * @return The {@link WeatherForecastDTO} weather forecast
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    WeatherForecastDTO getById(Long id);

    /**
     * Create a new WeatherForecast
     * @param weatherForecastDTO The WeatherForecast to create
     * @return The id of the created WeatherForecast
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Long create(WeatherForecastCreationDTO weatherForecastDTO);

    /**
     * Patches a WeatherForecast
     * @param id The id of the WeatherForecast
     * @param weatherForecastDTO The WeatherForecast to patch
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(Long id, WeatherForecastCreationDTO weatherForecastDTO);

    /**
     * Deletes a WeatherForecast
     * @param id The id of the WeatherForecast to delete
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long id);
}
