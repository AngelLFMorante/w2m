package com.w2m.app.web;

import com.w2m.app.application.service.SpacecraftService;
import com.w2m.app.domino.model.Spacecraft;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpacecraftControllerTest {

    @Mock
    private SpacecraftService spacecraftService;

    @InjectMocks
    private SpacecraftController spacecraftController;

    private Spacecraft spacecraft;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        spacecraft = new Spacecraft();
        spacecraft.setId(1L);
        spacecraft.setName("X-Wing");
        spacecraft.setType("Fighter");
        spacecraft.setOrigin("Rebel Alliance");
    }

    @Test
    void testGetAllSpacecrafts() {
        List<Spacecraft> spacecraftList = List.of(spacecraft);
        Page<Spacecraft> page = new PageImpl<>(spacecraftList);

        when(spacecraftService.getAllSpacecraft(pageable)).thenReturn(page);

        ResponseEntity<Page<Spacecraft>> response = spacecraftController.getAllSpacecrafts(pageable);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void testGetSpacecraftById_Success() {
        when(spacecraftService.getSpacecraftById(1L)).thenReturn(Optional.of(spacecraft));

        ResponseEntity<Spacecraft> response = spacecraftController.getSpacecraftById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("X-Wing", response.getBody().getName());
    }

    @Test
    void testGetSpacecraftById_NotFound() {
        when(spacecraftService.getSpacecraftById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> spacecraftController.getSpacecraftById(1L));
    }

    @Test
    void testSearchSpacecraftByName() {
        List<Spacecraft> spacecraftList = List.of(spacecraft);

        when(spacecraftService.searchSpacecraftByName("X-Wing")).thenReturn(spacecraftList);

        ResponseEntity<List<Spacecraft>> response = spacecraftController.searchSpacecraftByName("X-Wing");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testSaveSpacecraft() {
        when(spacecraftService.createSpacecraft(any(Spacecraft.class))).thenReturn(spacecraft);

        ResponseEntity<Spacecraft> response = spacecraftController.saveSpacecraft(spacecraft);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("X-Wing", response.getBody().getName());
    }

    @Test
    void testUpdateSpacecraft() {
        when(spacecraftService.updateSpacecraft(eq(1L), any(Spacecraft.class))).thenReturn(spacecraft);

        ResponseEntity<Spacecraft> response = spacecraftController.updateSpacecraft(1L, spacecraft);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("X-Wing", response.getBody().getName());
    }

    @Test
    void testDeleteSpacecraft() {
        doNothing().when(spacecraftService).deleteSpacecraft(1L);

        ResponseEntity<Void> response = spacecraftController.deleteSpacecraft(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}
