package me.simon.testapp.weatherforecast;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Simon Brebeck on 04/07/2023
 */
@Component
@RequiredArgsConstructor
public class WeatherForecastMapper {

    private final ModelMapper modelMapper;

    public WeatherForecastDTO toDTO(WeatherForecast weatherForecast) {
        return modelMapper.map(weatherForecast, WeatherForecastDTO.class);
    }

    public WeatherForecast toEntity(WeatherForecastCreationDTO weatherForecastCreationDTO) {
        return modelMapper.map(weatherForecastCreationDTO, WeatherForecast.class);
    }

}
