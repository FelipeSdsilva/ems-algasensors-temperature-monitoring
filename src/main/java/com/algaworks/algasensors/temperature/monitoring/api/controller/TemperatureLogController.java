package com.algaworks.algasensors.temperature.monitoring.api.controller;


import com.algaworks.algasensors.temperature.monitoring.api.model.output.TemperatureLogOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import com.algaworks.algasensors.temperature.monitoring.domain.repositories.TemperatureLogRepository;
import com.algaworks.algasensors.temperature.monitoring.domain.services.TemperatureLogService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class TemperatureLogController {

  private final TemperatureLogService logService;

  @GetMapping
  public ResponseEntity<Page<TemperatureLogOutput>> search(@PathVariable TSID sensorId,
                                                          @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(logService.search(sensorId, pageable));
  }

}