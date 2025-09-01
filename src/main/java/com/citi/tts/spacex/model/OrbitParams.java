package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrbitParams {
    private String reference_system;
    private String regime;
    private Float longitude;
    private Float semi_major_axis_km;
    private Float eccentricity;
    private Float periapsis_km;
    private Float apoapsis_km;
    private Float inclination_deg;
    private Float period_min;
    private Float lifespan_years;
    private String epoch;
    private Float mean_motion;
    private Float raan;
    private Float arg_of_pericenter;
    private Float mean_anomaly;
}
