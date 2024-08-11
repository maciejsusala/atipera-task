package pl.maciejsusala.atiperatask.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Feign clients.
 */
@Configuration
public class FeignConfig {

    /**
     * Provides a custom error decoder for Feign clients.
     *
     * @return an instance of {@link CustomFeignErrorDecoder}
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}