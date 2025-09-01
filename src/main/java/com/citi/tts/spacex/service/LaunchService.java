package com.citi.tts.spacex.service;

import com.citi.tts.spacex.model.Launch;
import com.citi.tts.spacex.model.LaunchInput;
import com.citi.tts.spacex.model.Order;
import com.citi.tts.spacex.repository.LaunchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaunchService {

    private final LaunchRepository launchRepository;
    private final LaunchMapper launchMapper;

    public List<Launch> getLaunches(Integer limit, Integer offset, String sort, Order order) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(order == Order.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, sort));
        return launchRepository.findAll(pageable).getContent();
    }

    public List<Launch> getLaunchesPast(Integer limit, Integer offset) {
        // Assuming upcoming=false for past launches
        // A more robust implementation would use the launch date
        return launchRepository.findAll();
    }

    public List<Launch> getLaunchesUpcoming(Integer limit, Integer offset) {
        // Assuming upcoming=true for upcoming launches
        return launchRepository.findAll();
    }

    public Optional<Launch> getLaunch(String id) {
        return launchRepository.findById(id);
    }

    public List<Launch> getLaunchesByMissionName(String missionName) {
        return launchRepository.findByMission_name(missionName);
    }

    public Launch createLaunch(LaunchInput launchInput) {
        Launch launch = launchMapper.toLaunch(launchInput);
        return launchRepository.save(launch);
    }

    public Optional<Launch> updateLaunch(String id, LaunchInput launchInput) {
        return launchRepository.findById(id).map(launch -> {
            launchMapper.updateLaunch(launch, launchInput);
            return launchRepository.save(launch);
        });
    }

    public boolean deleteLaunch(String id) {
        if (launchRepository.existsById(id)) {
            launchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
