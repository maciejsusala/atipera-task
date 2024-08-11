package pl.maciejsusala.atiperatask.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.maciejsusala.atiperatask.exception.CustomFeignException;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new CustomFeignException(response.status(), "Given user don't exist");
        }
        if (response.status() == 403) {
            return new CustomFeignException(response.status(), "Too many requests. Try again in 10 minutes");
        }
        return new CustomFeignException(response.status(), response.reason());
    }
}