package com.algaworks.algasensors.temperature.monitoring.api.model.output;

import com.algaworks.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureLogOutput {

  private UUID id;
  private TSID sensorId;
  private OffsetDateTime registeredAt;
  private Double value;

  public TemperatureLogOutput(TemperatureLog temperatureLog) {
    this.id = temperatureLog.getId().getValue();
    this.sensorId = temperatureLog.getSensorId().getValue();
    this.registeredAt = temperatureLog.getRegisteredAt();
    this.value = temperatureLog.getValue();
  }
}