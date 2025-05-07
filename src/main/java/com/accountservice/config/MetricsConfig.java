package com.accountservice.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing Micrometer metrics.
 * Adds common tags to all metrics for consistent labeling.
 */
@Configuration
public class MetricsConfig {
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(){
        return registry -> registry.config().commonTags("account-service","AccountServiceMetrics");
    }
}
