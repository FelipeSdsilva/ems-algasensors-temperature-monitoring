package com.algaworks.algasensors.temperature.monitoring.domain.services;

import com.algaworks.algasensors.temperature.monitoring.api.model.output.TemperatureLogData;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repositories.TemperatureLogRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemperatureLogService {

  private final TemperatureLogRepository logRepository;

  @Transactional(readOnly = true)
  public Page<TemperatureLogData> search(TSID sensorId, Pageable pageable) {
    return logRepository.findAllBySensorId(new SensorId(sensorId), pageable).map(TemperatureLogData::new);
  }
}
