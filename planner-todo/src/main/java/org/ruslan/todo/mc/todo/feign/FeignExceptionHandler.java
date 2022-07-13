package org.ruslan.todo.mc.todo.feign;

import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

@Component
public class FeignExceptionHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 404:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, readMessage(response));
            case 406:
                return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, readMessage(response));
            default:
                return null;
        }
    }

    // get the error text from the stream
    private String readMessage(Response response) {

        String message = null;

        try(Reader reader = response.body().asReader(Charset.defaultCharset())) {
            message = CharStreams.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}