package com.w2m.app.web;

import com.w2m.app.application.service.SpacecraftService;
import com.w2m.app.domino.Spacecraft;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("/api/spacecraft")
@Tag(name = "Spacecraft", description = "CRUD operations to manage spacecraft")
public class SpacecraftController {

    private final SpacecraftService service;

    public SpacecraftController(SpacecraftService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get all spacecraft", description = "get a paginated lis of all registered spacecraft")
    public ResponseEntity<Page<Spacecraft>> getAllSpacecrafts(Pageable pageable){
        return ResponseEntity.ok(service.getAllSpacecraft(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtain a spacecraft by ID", description = "Get the details of a specific spacecraft using its ID.")
    public ResponseEntity<Spacecraft> getSpacecraftById(
            @Parameter(description = "Id of the spacecraft to search for") @PathVariable Long id
    ){
        return service.getSpacecraftById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find")
    @Operation(summary = "Search for spacecraft by name", description = "Searches for spacecraft containing specific text in the name.")
    public ResponseEntity<List<Spacecraft>> searchSpacecraftByName(
            @Parameter(description = "Search for in the spacecraft name") @RequestParam String name
    ){
        return ResponseEntity.ok(service.searchSpacecraftByName(name));
    }

    @PostMapping
    @Operation(summary = "Create a new spacecraft", description = "Creates a new spacecraft in the system.")
    public ResponseEntity<Spacecraft> saveSpacecraft(@RequestBody Spacecraft spacecraft){
        return ResponseEntity.ok(service.createSpacecraft(spacecraft));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the details of an existing spacecraft.", description = "Updates the details of an existing spacecraft.")
    public ResponseEntity<Spacecraft> updateSpacecraft(
            @Parameter(description = "ID of the spacecraft to update")
            @PathVariable Long id, @RequestBody Spacecraft spacecraft
    ){
        return ResponseEntity.ok(service.updateSpacecraft(id, spacecraft));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "‘Delete a spacecraft", description = "Removes a specific spacecraft from the system.")
    public ResponseEntity<Void> deleteSpacecraft(
            @Parameter(description = "ID of the spacecraft to be deleted") @PathVariable Long id
    ){
        return ResponseEntity.noContent().build();
    }
}
