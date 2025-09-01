package com.citi.tts.spacex.service;

import com.citi.tts.spacex.model.Launch;
import com.citi.tts.spacex.model.LaunchInput;
import com.citi.tts.spacex.model.Order;
import com.citi.tts.spacex.repository.LaunchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaunchServiceTest {

    @Mock
    private LaunchRepository launchRepository;

    @Mock
    private LaunchMapper launchMapper;

    @InjectMocks
    private LaunchService launchService;

    private Launch launch;
    private LaunchInput launchInput;

    @BeforeEach
    void setUp() {
        launch = new Launch();
        launch.setId("1");
        launch.setMission_name("Test Mission");

        launchInput = new LaunchInput();
        launchInput.setMission_name("Test Mission");
    }

    @Test
    void testGetLaunches() {
        Page<Launch> page = new PageImpl<>(Collections.singletonList(launch));
        when(launchRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<Launch> result = launchService.getLaunches(1, 0, "id", Order.ASC);

        assertFalse(result.isEmpty());
        assertEquals("Test Mission", result.get(0).getMission_name());
        verify(launchRepository).findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
    }

    @Test
    void testGetLaunch() {
        when(launchRepository.findById("1")).thenReturn(Optional.of(launch));

        Optional<Launch> result = launchService.getLaunch("1");

        assertTrue(result.isPresent());
        assertEquals("Test Mission", result.get().getMission_name());
    }

    @Test
    void testGetLaunch_notFound() {
        when(launchRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Launch> result = launchService.getLaunch("1");

        assertFalse(result.isPresent());
    }

    @Test
    void testGetLaunchesByMissionName() {
        when(launchRepository.findByMission_name("Test Mission")).thenReturn(Collections.singletonList(launch));

        List<Launch> result = launchService.getLaunchesByMissionName("Test Mission");

        assertFalse(result.isEmpty());
        assertEquals("Test Mission", result.get(0).getMission_name());
    }

    @Test
    void testCreateLaunch() {
        when(launchMapper.toLaunch(launchInput)).thenReturn(launch);
        when(launchRepository.save(launch)).thenReturn(launch);

        Launch result = launchService.createLaunch(launchInput);

        assertNotNull(result);
        assertEquals("Test Mission", result.getMission_name());
    }

    @Test
    void testUpdateLaunch() {
        when(launchRepository.findById("1")).thenReturn(Optional.of(launch));
        when(launchRepository.save(launch)).thenReturn(launch);

        Optional<Launch> result = launchService.updateLaunch("1", launchInput);

        assertTrue(result.isPresent());
        verify(launchMapper).updateLaunch(launch, launchInput);
    }

    @Test
    void testUpdateLaunch_notFound() {
        when(launchRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Launch> result = launchService.updateLaunch("1", launchInput);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteLaunch() {
        when(launchRepository.existsById("1")).thenReturn(true);

        boolean result = launchService.deleteLaunch("1");

        assertTrue(result);
        verify(launchRepository).deleteById("1");
    }

    @Test
    void testDeleteLaunch_notFound() {
        when(launchRepository.existsById("1")).thenReturn(false);

        boolean result = launchService.deleteLaunch("1");

        assertFalse(result);
    }
}
