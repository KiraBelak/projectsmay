package org.example.demo;

import org.example.demo.model.Stats;
import org.example.demo.repository.StatsRepository;
import org.example.demo.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @InjectMocks
    private StatsService statsService;

    private Stats johnStats;

    @BeforeEach
    void setUp() {
        johnStats = new Stats(
                "John",
                10, 5, 2,
                1000, 750,
                200, 150, 60,
                "john.png",
                "Mike",
                0.75f
        );
    }

    @Test
    void getStatsByName_whenPresent_returnsStats() {
        when(statsRepository.findById("John")).thenReturn(Optional.of(johnStats));

        Stats result = statsService.getStatsByName("John");

        assertThat(result).isSameAs(johnStats);
        // verify findById("John") happened and nothing else
        verify(statsRepository).findById("John");
        verifyNoMoreInteractions(statsRepository);
    }

    @Test
    void getStatsByName_whenAbsent_returnsNull() {
        when(statsRepository.findById("Jane")).thenReturn(Optional.empty());

        Stats result = statsService.getStatsByName("Jane");

        assertThat(result).isNull();
        verify(statsRepository).findById("Jane");
    }

    @Test
    void saveStats_delegatesToRepository() {
        when(statsRepository.save(johnStats)).thenReturn(johnStats);

        Stats saved = statsService.saveStats(johnStats);

        assertThat(saved).isSameAs(johnStats);
        verify(statsRepository).save(johnStats);
    }

    @Test
    void getAllStats_returnsRepositoryResult() {
        List<Stats> list = Arrays.asList(johnStats, new Stats("Jane"));
        when(statsRepository.findAll()).thenReturn(list);

        List<Stats> result = statsService.getAllStats();

        assertThat(result).containsExactlyElementsOf(list);
        verify(statsRepository).findAll();
    }

}


