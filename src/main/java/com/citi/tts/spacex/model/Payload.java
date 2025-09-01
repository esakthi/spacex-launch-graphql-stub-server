package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private String payload_id;
    private List<Integer> norad_id;
    private Boolean reused;
    private List<String> customers;
    private String nationality;
    private String manufacturer;
    private String payload_type;
    private Float payload_mass_kg;
    private Float payload_mass_lbs;
    private String orbit;
    private OrbitParams orbit_params;
}
