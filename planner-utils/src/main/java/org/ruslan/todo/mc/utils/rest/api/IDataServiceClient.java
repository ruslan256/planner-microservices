package org.ruslan.todo.mc.utils.rest.api;

import reactor.core.publisher.Flux;

public interface IDataServiceClient {

    String baseUrlData = "http://localhost:8765/planner-todo/data/";

    Flux<Boolean> initUserDataAsync(Long userId);
}