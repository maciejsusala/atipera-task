package pl.maciejsusala.atiperatask.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.maciejsusala.atiperatask.exception.CustomFeignException;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new CustomFeignException(response.status(), response.reason());
    }
}