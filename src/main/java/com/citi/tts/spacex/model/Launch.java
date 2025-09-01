package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "launches")
public class Launch {
    @Id
    private String id;
    private String mission_name;
    private String launch_date_local;
    private String launch_date_utc;
    private String launch_year;
    private Boolean launch_success;
    private String details;
    private Boolean upcoming;
    private LaunchSite launch_site;
    private Links links;
    private Rocket rocket;
}
