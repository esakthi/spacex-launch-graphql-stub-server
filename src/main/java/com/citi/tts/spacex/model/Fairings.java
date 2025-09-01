package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fairings {
    private Boolean reused;
    private Boolean recovery_attempt;
    private Boolean recovered;
    private String ship;
}
