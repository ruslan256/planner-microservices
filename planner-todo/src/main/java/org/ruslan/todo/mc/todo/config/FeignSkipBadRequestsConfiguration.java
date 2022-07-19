package org.ruslan.todo.mc.todo.config;

import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class FeignSkipBadRequestsConfiguration {

    private final StoreErrorFallback storeErrorFallback;

    public FeignSkipBadRequestsConfiguration(StoreErrorFallback storeErrorFallback) {
        this.storeErrorFallback = storeErrorFallback;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            int status = response.status();
            String body = "Bad request";
            try {
                body = IOUtils.toString(response.body().asReader(StandardCharsets.UTF_8));
            } catch (Exception expIgnored) {
                log.warn("Not obtain body, error -> {}", expIgnored.getLocalizedMessage());
            }
            log.info("Response status = {}, body -> {}", status, body);
            HttpHeaders httpHeaders = new HttpHeaders();
            response.headers().forEach((k, v) -> httpHeaders.add(k, StringUtils.join(v, ", ")));
            if (status == 406) {
                String[] arrStrKey = body.split("\\s");
                for (String item : arrStrKey) {
                    if (item.matches("^[0-9]{1,20}")) {
                        Long key = Long.valueOf(item);
                        storeErrorFallback.addErrInStore(key, new FeignBadResponseWrapper(status, httpHeaders, body));
                    }
                }
            }
            return new ErrorDecoder.Default().decode(methodKey, response);
        };
    }
}