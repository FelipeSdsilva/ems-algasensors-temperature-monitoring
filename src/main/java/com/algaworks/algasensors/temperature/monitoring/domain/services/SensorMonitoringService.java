package com.algaworks.algasensors.temperature.monitoring.domain.services;

import com.algaworks.algasensors.temperature.monitoring.api.model.output.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.repositories.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SensorMonitoringService {

  private final SensorMonitoringRepository monitoringRepository;

  @Transactional(readOnly = true)
  public SensorMonitoringOutput getDetail(TSID sensorId) {
    SensorMonitoring monitoring = findByIdOrDefault(sensorId);
    return SensorMonitoringOutput.from(monitoring);
  }

  private SensorMonitoring findByIdOrDefault(TSID sensorId) {
    return monitoringRepository.findById(new SensorId(sensorId))
        .orElse(SensorMonitoring.builder()
            .id(new SensorId(sensorId))
            .enable(false)
            .build());
  }

  @Transactional
  public void enable(TSID sensorId) {
    SensorMonitoring monitoring = this.findByIdOrDefault(sensorId);
    monitoring.setEnable(true);
    monitoringRepository.saveAndFlush(monitoring);
  }

  @Transactional
  public void disable(TSID sensorId) {
    SensorMonitoring monitoring = this.findByIdOrDefault(sensorId);
    monitoring.setEnable(false);
    monitoringRepository.saveAndFlush(monitoring);
  }
}
