package com.algaworks.algasensors.temperature.monitoring.api.model.output;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOutput {

  private TSID id;
  private Double lastTemperature;
  private OffsetDateTime updateAt;
  private Boolean enable;

  public static SensorMonitoringOutput from(SensorMonitoring monitoring) {
    return SensorMonitoringOutput.builder()
        .id(monitoring.getId().getValue())
        .lastTemperature(monitoring.getLastTemperature())
        .updateAt(monitoring.getUpdatedAt())
        .enable(monitoring.getEnable())
        .build();
  }
}
