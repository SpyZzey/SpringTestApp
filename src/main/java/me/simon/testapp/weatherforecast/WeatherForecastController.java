package me.simon.testapp.weatherforecast;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * @author Simon Brebeck on 04/07/2023
 */
@Controller
@RequestMapping("/weatherforecast")
@RequiredArgsConstructor
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;

    /**
     * Returns a list of weather forecasts for the given date range.
     *
     * @param startDate The start date of the weather forecast
     * @param endDate The end date of the weather forecast
     * @return A {@link ResponseEntity} response entity with a list of {@link WeatherForecastDTO} weather forecasts
     */
    @GetMapping
    public ResponseEntity<List<WeatherForecastDTO>> getAllWeatherForecasts(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        return ResponseEntity.ok(weatherForecastService.getAll(startDate, endDate));
    }

    /**
     * Creates a new weather forecast.
     * @param weatherForecastDTO The weather forecast to create
     * @return A {@link ResponseEntity} response entity with 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Void> createWeatherForecast(@RequestBody WeatherForecastCreationDTO weatherForecastDTO) {
        Long id = weatherForecastService.create(weatherForecastDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }


    /**
     * Updates a weather forecast with the given id.
     *
     * @param id The id of the weather forecast
     * @return A {@link ResponseEntity} response entity with 200 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateWeatherForecast(@PathVariable Long id, @RequestBody WeatherForecastCreationDTO weatherForecastDTO) {
        weatherForecastService.update(id, weatherForecastDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Returns a single weather forecast for the given id.
     *
     * @param id The id of the weather forecast
     * @return A {@link ResponseEntity} response entity with a {@link WeatherForecastDTO} weather forecast
     */
    @GetMapping("/{id}")
    public ResponseEntity<WeatherForecastDTO> getWeatherForecastById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherForecastService.getById(id));
    }

    /**
     * Deletes a weather forecast with the given id.
     *
     * @param id The id of the weather forecast
     * @return A {@link ResponseEntity} response entity with 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherForecast(@PathVariable Long id) {
        weatherForecastService.delete(id);
        return ResponseEntity.ok().build();
    }

}
