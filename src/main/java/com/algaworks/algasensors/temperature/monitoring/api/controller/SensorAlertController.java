package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.model.input.SensorAlertInput;
import com.algaworks.algasensors.temperature.monitoring.api.model.output.SensorAlertOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.services.SensorAlertService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

  private final SensorAlertService alertService;

  @GetMapping
  public ResponseEntity<SensorAlertOutput> getOne(@PathVariable TSID sensorId) {
    return ResponseEntity.ok(alertService.getOne(sensorId));
  }

  @PutMapping
  public ResponseEntity<SensorAlertOutput> updateOrCreate(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input) {
    return ResponseEntity.ok(alertService.updateOrCreate(sensorId, input));
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@PathVariable TSID sensorId) {
    alertService.delete(sensorId);
    return ResponseEntity.noContent().build();
  }
}

