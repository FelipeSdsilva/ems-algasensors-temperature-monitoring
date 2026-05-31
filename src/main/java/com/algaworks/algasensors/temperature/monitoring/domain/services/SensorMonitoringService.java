package com.algaworks.algasensors.temperature.monitoring.domain.services;

import com.algaworks.algasensors.temperature.monitoring.api.model.output.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.repositories.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

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
        if (monitoring.getEnable()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Monitoring already enabled");
        }
        monitoring.setEnable(true);
        monitoringRepository.saveAndFlush(monitoring);
    }

    @SneakyThrows
    @Transactional
    public void disable(TSID sensorId) {
        SensorMonitoring monitoring = this.findByIdOrDefault(sensorId);
        if (!monitoring.getEnable()) {
            Thread.sleep(Duration.ofSeconds(10));
        }
        monitoring.setEnable(false);
        monitoringRepository.saveAndFlush(monitoring);
    }
}
