package com.citi.tts.spacex.controller;

import com.citi.tts.spacex.model.Launch;
import com.citi.tts.spacex.model.LaunchInput;
import com.citi.tts.spacex.model.Order;
import com.citi.tts.spacex.service.LaunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LaunchController {

    private final LaunchService launchService;
    private final Sinks.Many<Launch> launchSink = Sinks.many().multicast().onBackpressureBuffer();

    @QueryMapping
    public List<Launch> launches(@Argument Integer limit, @Argument Integer offset, @Argument String sort, @Argument Order order) {
        return launchService.getLaunches(limit, offset, sort, order);
    }

    @QueryMapping
    public List<Launch> launchesPast(@Argument Integer limit, @Argument Integer offset) {
        return launchService.getLaunchesPast(limit, offset);
    }

    @QueryMapping
    public List<Launch> launchesUpcoming(@Argument Integer limit, @Argument Integer offset) {
        return launchService.getLaunchesUpcoming(limit, offset);
    }

    @QueryMapping
    public Launch launch(@Argument String id) {
        return launchService.getLaunch(id).orElse(null);
    }

    @QueryMapping
    public List<Launch> launchesByMissionName(@Argument String missionName) {
        return launchService.getLaunchesByMissionName(missionName);
    }

    @MutationMapping
    public Launch createLaunch(@Argument LaunchInput input) {
        Launch launch = launchService.createLaunch(input);
        launchSink.tryEmitNext(launch);
        return launch;
    }

    @MutationMapping
    public Launch updateLaunch(@Argument String id, @Argument LaunchInput input) {
        return launchService.updateLaunch(id, input).orElse(null);
    }

    @MutationMapping
    public boolean deleteLaunch(@Argument String id) {
        return launchService.deleteLaunch(id);
    }

    @SubscriptionMapping
    public Flux<Launch> launchAdded() {
        return launchSink.asFlux();
    }

    @SubscriptionMapping
    public Flux<Launch> launchUpdated(@Argument String id) {
        // A more sophisticated implementation would filter by id
        return launchSink.asFlux();
    }
}
