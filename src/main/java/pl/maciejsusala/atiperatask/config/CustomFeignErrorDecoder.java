package pl.maciejsusala.atiperatask.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.maciejsusala.atiperatask.exception.CustomFeignException;

/**
 * Custom Feign error decoder to handle specific HTTP status codes.
 */
public class CustomFeignErrorDecoder implements ErrorDecoder {

    /**
     * Decodes the HTTP response and returns a custom exception based on the status code.
     *
     * @param methodKey methodKey the Feign client method key, which is a combination of the Feign client interface class name and the method name
     * @param response the HTTP response
     * @return an exception based on the HTTP status code
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new CustomFeignException(response.status(), "Given user doesn't exist");
        }
        if (response.status() == 403) {
            return new CustomFeignException(response.status(), "Too many requests. Try again in 10 minutes");
        }
        return new CustomFeignException(response.status(), response.reason());
    }
}