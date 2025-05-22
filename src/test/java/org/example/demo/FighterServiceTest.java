package org.example.demo;

import org.example.demo.model.Fighter;
import org.example.demo.repository.FighterRepository;
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

import org.example.demo.service.FighterService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link FighterService}.
 * */
@ExtendWith(MockitoExtension.class)
class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @InjectMocks
    private FighterService fighterService;

    private Fighter f1;
    private Fighter f2;

    @BeforeEach
    void setUp() {
        f1 = new Fighter();
        f2 = new Fighter();
    }

    @Test
    void getAllFighters_returns_everything_from_repository() {
        when(fighterRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<Fighter> result = fighterService.getAllFighters();

        assertThat(result).containsExactly(f1, f2);
        verify(fighterRepository).findAll();
    }

    @Test
    void addFighter_persists_and_returns_saved_entity() {
        when(fighterRepository.save(f1)).thenReturn(f1);

        Fighter saved = fighterService.addFighter(f1);

        // capture the argument actually handed to save(..)
        ArgumentCaptor<Fighter> captor = ArgumentCaptor.forClass(Fighter.class);
        verify(fighterRepository).save(captor.capture());

        assertThat(captor.getValue()).isEqualTo(f1);
        assertThat(saved).isSameAs(f1);
    }

    @Test
    void getFighterById_when_found_returns_entity() {
        when(fighterRepository.findById(1L)).thenReturn(Optional.of(f1));

        Fighter found = fighterService.getFighterById(1L);

        assertThat(found).isEqualTo(f1);
        verify(fighterRepository).findById(1L);
    }

    @Test
    void getFighterById_when_not_found_returns_null() {
        when(fighterRepository.findById(99L)).thenReturn(Optional.empty());

        Fighter found = fighterService.getFighterById(99L);

        assertThat(found).isNull();
        verify(fighterRepository).findById(99L);
    }
}


