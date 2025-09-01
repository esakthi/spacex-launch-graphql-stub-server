package com.citi.tts.spacex.controller;

import com.citi.tts.spacex.model.Launch;
import com.citi.tts.spacex.service.LaunchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Sinks;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@GraphQlTest(LaunchController.class)
@Import(LaunchControllerTest.TestConfig.class)
class LaunchControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private LaunchService launchService;

    private Launch launch;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public Sinks.Many<Launch> launchSink() {
            return Sinks.many().multicast().onBackpressureBuffer();
        }
    }

    @BeforeEach
    void setUp() {
        launch = new Launch();
        launch.setId("1");
        launch.setMission_name("Test Mission");
    }

    @Test
    void testLaunchesQuery() {
        when(launchService.getLaunches(any(), any(), any(), any())).thenReturn(Collections.singletonList(launch));

        graphQlTester.documentName("launches")
                .variable("limit", 1)
                .variable("offset", 0)
                .variable("sort", "id")
                .variable("order", "ASC")
                .execute()
                .path("launches[0].mission_name")
                .entity(String.class)
                .isEqualTo("Test Mission");
    }

    @Test
    void testLaunchQuery() {
        when(launchService.getLaunch("1")).thenReturn(Optional.of(launch));

        graphQlTester.documentName("launch")
                .variable("id", "1")
                .execute()
                .path("launch.mission_name")
                .entity(String.class)
                .isEqualTo("Test Mission");
    }

    @Test
    void testLaunchQuery_notFound() {
        when(launchService.getLaunch("1")).thenReturn(Optional.empty());

        graphQlTester.documentName("launch")
                .variable("id", "1")
                .execute()
                .path("launch")
                .valueIsNull();
    }

    @Test
    void testCreateLaunchMutation() {
        when(launchService.createLaunch(any())).thenReturn(launch);

        graphQlTester.documentName("createLaunch")
                .variable("mission_name", "Test Mission")
                .execute()
                .path("createLaunch.mission_name")
                .entity(String.class)
                .isEqualTo("Test Mission");
    }

    @Test
    void testUpdateLaunchMutation() {
        when(launchService.updateLaunch(any(), any())).thenReturn(Optional.of(launch));

        graphQlTester.documentName("updateLaunch")
                .variable("id", "1")
                .variable("mission_name", "Updated Mission")
                .execute()
                .path("updateLaunch.mission_name")
                .entity(String.class)
                .isEqualTo("Test Mission");
    }

    @Test
    void testDeleteLaunchMutation() {
        when(launchService.deleteLaunch("1")).thenReturn(true);

        graphQlTester.documentName("deleteLaunch")
                .variable("id", "1")
                .execute()
                .path("deleteLaunch")
                .entity(boolean.class)
                .isEqualTo(true);
    }
}
