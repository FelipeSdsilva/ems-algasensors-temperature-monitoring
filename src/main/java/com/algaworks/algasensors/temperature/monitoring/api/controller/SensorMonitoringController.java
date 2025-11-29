package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.model.output.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.services.SensorMonitoringService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/sensors/{sensorId}/monitoring")
public class SensorMonitoringController {

  private final SensorMonitoringService monitoringService;

  @GetMapping
  public ResponseEntity<SensorMonitoringOutput> getDetail(@PathVariable TSID sensorId) {
    return ResponseEntity.ok(monitoringService.getDetail(sensorId));
  }

  @PutMapping(value = "/enable")
  public ResponseEntity<Void> enable(@PathVariable TSID sensorId) {
    monitoringService.enable(sensorId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/enable")
  public ResponseEntity<Void> disable(@PathVariable TSID sensorId) {
    monitoringService.disable(sensorId);
    return ResponseEntity.noContent().build();
  }
}
