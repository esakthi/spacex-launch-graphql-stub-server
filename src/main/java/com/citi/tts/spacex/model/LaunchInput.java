package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchInput {
    private String mission_name;
    private String launch_date_local;
    private String launch_date_utc;
    private String launch_year;
    private Boolean launch_success;
    private String details;
    private Boolean upcoming;
    private LaunchSiteInput launch_site;
    private LinksInput links;
    private RocketInput rocket;
}
