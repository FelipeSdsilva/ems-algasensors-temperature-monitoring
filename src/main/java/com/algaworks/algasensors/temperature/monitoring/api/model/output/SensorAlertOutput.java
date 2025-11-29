package com.algaworks.algasensors.temperature.monitoring.api.model.output;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorAlertOutput {

  private TSID id;
  private Double maxTemperature;
  private Double minTemperature;

  public SensorAlertOutput(SensorAlert sensorAlert) {
    this.id = sensorAlert.getId().getValue();
    BeanUtils.copyProperties(sensorAlert, this);
  }
}
