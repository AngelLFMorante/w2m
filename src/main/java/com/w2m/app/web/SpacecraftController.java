package com.w2m.app.web;

import com.w2m.app.application.service.SpacecraftService;
import com.w2m.app.domino.model.Spacecraft;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Spacecraft controller.
 *
 * @author Angel Lf Morante
 * @version 1.0
 */
@RestController
@RequestMapping("/api/spacecraft")
@Tag(name = "Spacecraft", description = "CRUD operations to manage spacecraft")
public class SpacecraftController {

    private static final Logger logger = LoggerFactory.getLogger(SpacecraftController.class);

    private final SpacecraftService service;

    public SpacecraftController(SpacecraftService service) {
        this.service = service;
    }

    /**
     * Retrieves a paginated list of all spacecrafts.
     *
     * @param pageable Pageable object to apply pagination parameters
     * @return ResponseEntity with the list of spacecrafts
     */
    @GetMapping
    @Operation(summary = "get all spacecraft", description = "get a paginated lis of all registered spacecraft")
    public ResponseEntity<Page<Spacecraft>> getAllSpacecrafts(Pageable pageable){
        logger.info("Fetching all spacecraft with pagination");
        return ResponseEntity.ok(service.getAllSpacecraft(pageable));
    }

    /**
     * Retrieves a spacecraft by its ID.
     *
     * @param id The ID of the spacecraft to be fetched
     * @return ResponseEntity with the spacecraft details, or a 404 error if not found
     * @throws EntityNotFoundException If the spacecraft with the given ID is not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtain a spacecraft by ID", description = "Get the details of a specific spacecraft using its ID.")
    public ResponseEntity<Spacecraft> getSpacecraftById(
            @Parameter(description = "Id of the spacecraft to search for") @PathVariable Long id
    ){
        logger.info("Fetching spacecraft with ID: {}", id);
        return service.getSpacecraftById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(
                    () -> {
                        logger.error("Spacecraft not found with id: {}", id);
                        return new EntityNotFoundException("Spacecraft not found with id: " + id);
                    }
                );
    }

    /**
     * Searches for spacecraft by their name.
     *
     * @param name Name (or part of name) to search for in spacecraft names
     * @return ResponseEntity with a list of spacecraft matching the search criteria
     */
    @GetMapping("/find")
    @Operation(summary = "Search for spacecraft by name", description = "Searches for spacecraft containing specific text in the name.")
    public ResponseEntity<List<Spacecraft>> searchSpacecraftByName(
            @Parameter(description = "Search for in the spacecraft name") @RequestParam String name
    ){
        logger.info("Searching spacecraft by name: {}", name);
        return ResponseEntity.ok(service.searchSpacecraftByName(name));
    }

    /**
     * Creates a new spacecraft in the system.
     *
     * @param spacecraft The spacecraft object to be created
     * @return ResponseEntity with the created spacecraft and a 201 status code
     */
    @PostMapping
    @Operation(summary = "Create a new spacecraft", description = "Creates a new spacecraft in the system.")
    public ResponseEntity<Spacecraft> saveSpacecraft(@RequestBody Spacecraft spacecraft){
        logger.info("Creating new spacecraft with name: {}", spacecraft.getName());
        Spacecraft createdSpacecraft = service.createSpacecraft(spacecraft);
        return new ResponseEntity<>(createdSpacecraft, HttpStatus.CREATED);
    }

    /**
     * Updates the details of an existing spacecraft.
     *
     * @param id The ID of the spacecraft to be updated
     * @param spacecraft The updated spacecraft object
     * @return ResponseEntity with the updated spacecraft details
     */
    @PutMapping("/{id}")
    @Operation(summary = "Updates the details of an existing spacecraft.", description = "Updates the details of an existing spacecraft.")
    @CacheEvict(value = "spacecraftById", key = "#id")
    public ResponseEntity<Spacecraft> updateSpacecraft(
            @Parameter(description = "ID of the spacecraft to update")
            @PathVariable Long id, @RequestBody Spacecraft spacecraft
    ){
        logger.info("Updating spacecraft with ID: {}", id);
        return ResponseEntity.ok(service.updateSpacecraft(id, spacecraft));
    }

    /**
     * Deletes a spacecraft from the system.
     *
     * @param id The ID of the spacecraft to be deleted
     * @return ResponseEntity with no content and a 204 status code
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "‘Delete a spacecraft", description = "Removes a specific spacecraft from the system.")
    @CacheEvict(value = "spacecraftById", key = "#id")
    public ResponseEntity<Void> deleteSpacecraft(
            @Parameter(description = "ID of the spacecraft to be deleted") @PathVariable Long id
    ){
        logger.info("Deleting spacecraft with ID: {}", id);
        service.deleteSpacecraft(id);
        return ResponseEntity.noContent().build();
    }
}
