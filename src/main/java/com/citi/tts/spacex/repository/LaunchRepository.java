package com.citi.tts.spacex.repository;

import com.citi.tts.spacex.model.Launch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaunchRepository extends MongoRepository<Launch, String> {
    List<Launch> findByMission_name(String missionName);
}
