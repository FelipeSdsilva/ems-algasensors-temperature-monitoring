package com.algaworks.algasensors.temperature.monitoring.infrastructure.rabbitmq;

import com.algaworks.algasensors.temperature.monitoring.api.model.output.TemperatureLogData;
import com.algaworks.algasensors.temperature.monitoring.domain.services.TemperatureMonitoringService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;

    @SneakyThrows
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handler(@Payload TemperatureLogData temperatureLogData, @Headers Map<String, Object> headers) {
        TSID sensorId = temperatureLogData.getSensorId();
        Double temperature = temperatureLogData.getValue();
        log.info("Temperature updated: from sensor {} with temperature {}", sensorId, temperature);
        log.info("Headers: {}", headers);

        temperatureMonitoringService.processTemperatureReading(temperatureLogData);

    }
}
