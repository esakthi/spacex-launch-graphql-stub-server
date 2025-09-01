package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Core {
    private String core_serial;
    private Integer flight;
    private Integer block;
    private Boolean gridfins;
    private Boolean legs;
    private Boolean reused;
    private Boolean land_success;
    private Boolean landing_intent;
    private String landing_type;
    private String landing_vehicle;
}
