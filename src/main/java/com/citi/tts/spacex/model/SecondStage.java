package com.citi.tts.spacex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondStage {
    private Integer block;
    private List<Payload> payloads;
}
