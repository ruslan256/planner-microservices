package org.ruslan.todo.mc.todo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class StoreErrorFallback {

    public static final ConcurrentHashMap<Long, FeignBadResponseWrapper> STORE_ERROR_FALLBACK = new ConcurrentHashMap<>();

    public void addErrInStore(Long key, FeignBadResponseWrapper wrapper) {
        STORE_ERROR_FALLBACK.put(key, wrapper);
        log.info("Put into the Err store (Circuit Breaker), key = {}, length = {}", key, getCountItemStore());
    }

    public void removeErrFromStore(Long key) {
        STORE_ERROR_FALLBACK.remove(key);
        log.info("Length of the Err store (Circuit Breaker) after removing equals {}", getCountItemStore());
    }

    public FeignBadResponseWrapper getErrFromStore(Long key) {
        return STORE_ERROR_FALLBACK.get(key);
    }

    public int getCountItemStore() {
        return STORE_ERROR_FALLBACK.size();
    }
}