package com.baeldung.webflux.api;

import com.baeldung.webflux.model.WeatherEvent;
import com.baeldung.webflux.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalTime;
import java.util.UUID;

@RestController
@CrossOrigin
public class ServerSentEventController {

    // https://www.baeldung.com/spring-server-sent-events

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/stream-sse")
    public Flux<ServerSentEvent<String>> streamData() {
        return weatherService.streamWeather()
                             .map(we -> ServerSentEvent.<String>builder()
                                     .id(UUID.randomUUID().toString())
                                     .event("periodic-event")
                                     .data("SSE - " + we.toString())
                                     .build());
    }
}
