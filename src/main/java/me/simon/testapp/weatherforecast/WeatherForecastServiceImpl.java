package me.simon.testapp.weatherforecast;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Simon Brebeck on 04/07/2023
 */
@Service
@RequiredArgsConstructor
public class WeatherForecastServiceImpl implements WeatherForecastService {

    private final WeatherForecastDAO weatherForecastDAO;
    private final WeatherForecastMapper weatherForecastMapper;

    @Override
    public List<WeatherForecastDTO> getAll(Date startDate, Date endDate) {
        if(startDate == null) startDate = new Date(0); // 1970-01-01 (00:00:00)
        if(endDate == null) endDate = new Date(4102444800000L); // 2099-12-31 (23:59:59)
        List<WeatherForecast> weatherForecasts = weatherForecastDAO.findAllByDateBetween(startDate, endDate);
        return weatherForecasts.stream().map(weatherForecastMapper::toDTO).toList();
    }

    @Override
    public WeatherForecastDTO getById(Long id) {
        return weatherForecastDAO.findById(id).map(weatherForecastMapper::toDTO).orElse(null);
    }

    @Override
    public Long create(WeatherForecastCreationDTO weatherForecastDTO) {
        WeatherForecast weatherForecast = weatherForecastMapper.toEntity(weatherForecastDTO);
        weatherForecastDAO.save(weatherForecast);
        return weatherForecast.getId();
    }

    @Override
    public void update(Long id, WeatherForecastCreationDTO weatherForecastDTO) {
        // Todo: Implement
        return;
    }

    @Override
    public void delete(Long id) {
        weatherForecastDAO.deleteById(id);
    }
}
