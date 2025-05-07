package com.accountservice.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MetricsConfigTest {
    @Test
    void testMetricsCommonTags(){
        MetricsConfig config = new MetricsConfig();

        MeterRegistryCustomizer<MeterRegistry> customizer = config.metricsCommonTags();
        MeterRegistry registry = new SimpleMeterRegistry();

        customizer.customize(registry);
        registry.counter("test.metric");
        assertTrue(registry.get("test.metric").tags("account-service","AccountServiceMetrics").counter().count()==0.0);
    }
}
