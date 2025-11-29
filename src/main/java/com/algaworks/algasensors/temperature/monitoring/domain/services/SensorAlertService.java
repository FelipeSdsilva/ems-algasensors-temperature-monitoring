package com.algaworks.algasensors.temperature.monitoring.domain.services;

import com.algaworks.algasensors.temperature.monitoring.api.model.input.SensorAlertInput;
import com.algaworks.algasensors.temperature.monitoring.api.model.output.SensorAlertOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repositories.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SensorAlertService {

  private final SensorAlertRepository alertRepository;

  private SensorAlert retrieverEntityPerId(TSID sensorId) {
    return alertRepository.findById(new SensorId(sensorId))
        .orElse(SensorAlert.builder()
            .id(new SensorId(sensorId))
            .maxTemperature(null)
            .maxTemperature(null)
            .build());
  }

  @Transactional(readOnly = true)
  public SensorAlertOutput getOne(TSID sensorId) {
    SensorAlert alert = alertRepository.findById(new SensorId(sensorId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return new SensorAlertOutput(alert);
  }

  @Transactional
  public SensorAlertOutput updateOrCreate(TSID sensorId, SensorAlertInput input) {
    SensorAlert alert = retrieverEntityPerId(sensorId);
    alert.setMaxTemperature(input.getMaxTemperature());
    alert.setMinTemperature(input.getMinTemperature());
    alertRepository.saveAndFlush(alert);
    return new SensorAlertOutput(alert);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(TSID sensorId) {
    if (!alertRepository.existsById(new SensorId(sensorId)))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    alertRepository.deleteById(new SensorId(sensorId));
  }

}
