package com.w2m.app.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.w2m.app.domino.model.Spacecraft;
import com.w2m.app.domino.repository.SpacecraftRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SpacecraftServiceTest {

    @Mock
    private SpacecraftRepository spacecraftRepository;

    @InjectMocks
    private SpacecraftService spacecraftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSpacecraftById_ShouldReturnSpacecraft_WhenFound() {
        // Arrange
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setId(1L);
        spacecraft.setName("X-Wing");

        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));

        // Act
        Optional<Spacecraft> result = spacecraftService.getSpacecraftById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("X-Wing", result.get().getName());
    }
}
