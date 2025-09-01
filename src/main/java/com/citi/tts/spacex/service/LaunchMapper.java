package com.citi.tts.spacex.service;

import com.citi.tts.spacex.model.Launch;
import com.citi.tts.spacex.model.LaunchInput;
import org.springframework.stereotype.Component;

@Component
public class LaunchMapper {

    public Launch toLaunch(LaunchInput input) {
        Launch launch = new Launch();
        launch.setMission_name(input.getMission_name());
        launch.setLaunch_date_local(input.getLaunch_date_local());
        launch.setLaunch_date_utc(input.getLaunch_date_utc());
        launch.setLaunch_year(input.getLaunch_year());
        launch.setLaunch_success(input.getLaunch_success());
        launch.setDetails(input.getDetails());
        launch.setUpcoming(input.getUpcoming());
        // Mapping for nested objects needs to be handled here
        return launch;
    }

    public void updateLaunch(Launch launch, LaunchInput input) {
        launch.setMission_name(input.getMission_name());
        launch.setLaunch_date_local(input.getLaunch_date_local());
        launch.setLaunch_date_utc(input.getLaunch_date_utc());
        launch.setLaunch_year(input.getLaunch_year());
        launch.setLaunch_success(input.getLaunch_success());
        launch.setDetails(input.getDetails());
        launch.setUpcoming(input.getUpcoming());
        // Mapping for nested objects needs to be handled here
    }
}
