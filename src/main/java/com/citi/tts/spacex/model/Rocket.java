package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rocket {
    private String rocket_id;
    private String rocket_name;
    private String rocket_type;
    private FirstStage first_stage;
    private SecondStage second_stage;
    private Fairings fairings;
}
