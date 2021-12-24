package com.baeldung.webflux.api;

import com.baeldung.webflux.model.WeatherEvent;
import com.baeldung.webflux.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class StreamController {

    @Autowired
    private RequestHandler requestHandler;

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/stream-data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherEvent> streamData() {
        return weatherService.streamWeather();
    }
}
