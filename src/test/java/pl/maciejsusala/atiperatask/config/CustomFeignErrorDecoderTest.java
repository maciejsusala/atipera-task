package pl.maciejsusala.atiperatask.config;

import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.maciejsusala.atiperatask.exception.CustomFeignException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomFeignErrorDecoderTest {

    private CustomFeignErrorDecoder errorDecoder;

    @BeforeEach
    public void setUp() {
        errorDecoder = new CustomFeignErrorDecoder();
    }

    @Test
    public void testDecode_404() {
        Response response = mock(Response.class);
        when(response.status()).thenReturn(404);
        when(response.reason()).thenReturn("Not Found");

        Exception exception = errorDecoder.decode("methodKey", response);

        assertInstanceOf(CustomFeignException.class, exception);
        assertEquals(404, ((CustomFeignException) exception).status());
        assertEquals("Given user doesn't exist", exception.getMessage());
    }

    @Test
    public void testDecode_403() {
        // Arrange
        Response response = mock(Response.class);
        when(response.status()).thenReturn(403);
        when(response.reason()).thenReturn("Forbidden");

        // Act
        Exception exception = errorDecoder.decode("methodKey", response);

        // Assert
        assertInstanceOf(CustomFeignException.class, exception);
        assertEquals(403, ((CustomFeignException) exception).status());
        assertEquals("Too many requests. Try again in 10 minutes", exception.getMessage());
    }

    @Test
    public void testDecode_OtherStatus() {
        // Arrange
        Response response = mock(Response.class);
        when(response.status()).thenReturn(500);
        when(response.reason()).thenReturn("Internal Server Error");

        // Act
        Exception exception = errorDecoder.decode("methodKey", response);

        // Assert
        assertInstanceOf(CustomFeignException.class, exception);
        assertEquals(500, ((CustomFeignException) exception).status());
        assertEquals("Internal Server Error", exception.getMessage());
    }
}