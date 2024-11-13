package com.w2m.app.application.service;

import com.w2m.app.application.exception.NegativeIdException;
import com.w2m.app.domino.model.Spacecraft;
import com.w2m.app.domino.repository.SpacecraftRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpacecraftServiceTest {

    @Mock
    private SpacecraftRepository spacecraftRepository;

    @InjectMocks
    private SpacecraftService spacecraftService;

    private Spacecraft spacecraft;

    @BeforeEach
    void setUp() {
        spacecraft = new Spacecraft(1L, "Enterprise", "Fighter", "Earth");
    }

    @Test
    void testGetAllSpacecraft() {
        Page<Spacecraft> spacecraftPage = new PageImpl<>(Arrays.asList(spacecraft));
        when(spacecraftRepository.findAll(any(Pageable.class))).thenReturn(spacecraftPage);

        Page<Spacecraft> result = spacecraftService.getAllSpacecraft(mock(Pageable.class));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(spacecraftRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetSpacecraftById() {
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));

        Optional<Spacecraft> result = spacecraftService.getSpacecraftById(1L);

        assertTrue(result.isPresent());
        assertEquals(spacecraft, result.get());
        verify(spacecraftRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSpacecraftByIdNegativeId() {
        assertThrows(NegativeIdException.class, () -> spacecraftService.getSpacecraftById(-1L));
        verify(spacecraftRepository, times(0)).findById(anyLong());
    }

    @Test
    void testSearchSpacecraftByName() {
        when(spacecraftRepository.findByNameContaining("Enterprise")).thenReturn(Arrays.asList(spacecraft));

        var result = spacecraftService.searchSpacecraftByName("Enterprise");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(spacecraftRepository, times(1)).findByNameContaining("Enterprise");
    }

    @Test
    void testCreateSpacecraft() {
        when(spacecraftRepository.save(any(Spacecraft.class))).thenReturn(spacecraft);

        Spacecraft result = spacecraftService.createSpacecraft(spacecraft);

        assertNotNull(result);
        assertEquals("Enterprise", result.getName());
        verify(spacecraftRepository, times(1)).save(any(Spacecraft.class));
    }

    @Test
    void testUpdateSpacecraft() {
        Spacecraft updatedSpacecraft = new Spacecraft(1L, "Discovery", "Explorer", "Mars");
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));
        when(spacecraftRepository.save(any(Spacecraft.class))).thenReturn(updatedSpacecraft);

        Spacecraft result = spacecraftService.updateSpacecraft(1L, updatedSpacecraft);

        assertNotNull(result);
        assertEquals("Discovery", result.getName());
        verify(spacecraftRepository, times(1)).findById(1L);
        verify(spacecraftRepository, times(1)).save(any(Spacecraft.class));
    }

    @Test
    void testUpdateSpacecraftNotFound() {
        Spacecraft updatedSpacecraft = new Spacecraft(1L, "Discovery", "Explorer", "Mars");
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> spacecraftService.updateSpacecraft(1L, updatedSpacecraft));
        verify(spacecraftRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteSpacecraft() {
        when(spacecraftRepository.existsById(1L)).thenReturn(true);

        spacecraftService.deleteSpacecraft(1L);

        verify(spacecraftRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSpacecraftNotFound() {
        when(spacecraftRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> spacecraftService.deleteSpacecraft(1L));
        verify(spacecraftRepository, times(0)).deleteById(1L);
    }
}
