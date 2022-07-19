package org.ruslan.todo.mc.todo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

@Getter
@Setter
public class FeignBadResponseWrapper {

    private final int status;
    private final HttpHeaders headers;
    private final String body;

    public FeignBadResponseWrapper(int status, HttpHeaders headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeignBadResponseWrapper that = (FeignBadResponseWrapper) o;
        return status == that.status && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, body);
    }
}
