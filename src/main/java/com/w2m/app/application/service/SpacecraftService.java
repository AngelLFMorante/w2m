package com.w2m.app.application.service;

import com.w2m.app.application.exception.NegativeIdException;
import com.w2m.app.domino.model.Spacecraft;
import com.w2m.app.domino.repository.SpacecraftRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing spacecraft operations, including retrieving,
 * creating, updating, and deleting spacecraft data. It also handles
 * searching for spacecraft by name and caching the results.
 *
 * @author Angel Lf Morante
 * @version 1.0
 */
@Service
public class SpacecraftService {

    private final SpacecraftRepository spacecraftRepository;
    private final Logger logger = LoggerFactory.getLogger(SpacecraftService.class);

    /**
     * Constructs a new SpacecraftService with the specified spacecraft repository.
     *
     * @param spacecraftRepository the spacecraft repository to be used by the service
     */
    public SpacecraftService(SpacecraftRepository spacecraftRepository) {
        this.spacecraftRepository = spacecraftRepository;
    }

    /**
     * Retrieves all spacecrafts with pagination.
     *
     * @param pageable the pagination information
     * @return a page of spacecrafts
     */
    public Page<Spacecraft> getAllSpacecraft(Pageable pageable){
        logger.info("Fetching all spacecraft with pagination: {}", pageable);
        return spacecraftRepository.findAll(pageable);
    }

    /**
     * Retrieves a spacecraft by its ID, using caching to improve performance.
     *
     * @param id the ID of the spacecraft to retrieve
     * @return an optional containing the spacecraft, or empty if not found
     * @throws NegativeIdException if the provided ID is negative
     */
    @Cacheable("spacecraftById")
    public Optional<Spacecraft> getSpacecraftById(Long id){
        if (id < 0) {
            logger.error("Attempted to fetch spacecraft with negative ID: {}", id);
            throw new NegativeIdException("El ID proporcionado no puede ser negativo: " + id);
        }
        logger.info("Fetching spacecraft with ID: {}", id);
        return spacecraftRepository.findById(id);
    }

    /**
     * Searches for spacecrafts by name, using caching to improve performance.
     *
     * @param name the name or partial name to search for
     * @return a list of spacecrafts that contain the specified name
     */
    @Cacheable("spacecraftByName")
    public List<Spacecraft> searchSpacecraftByName(String name){
        logger.info("Searching for spacecraft with name containing: {}", name);
        return spacecraftRepository.findByNameContaining(name);
    }

    /**
     * Creates a new spacecraft in the system.
     *
     * @param spacecraft the spacecraft to be created
     * @return the created spacecraft
     */
    public Spacecraft createSpacecraft(Spacecraft spacecraft){
        logger.info("Creating new spacecraft with name: {}", spacecraft.getName());
        return spacecraftRepository.save(spacecraft);
    }

    /**
     * Updates the details of an existing spacecraft. If the spacecraft with the
     * given ID does not exist, an exception is thrown.
     *
     * @param id the ID of the spacecraft to update
     * @param spacecraft the new spacecraft data
     * @return the updated spacecraft
     * @throws EntityNotFoundException if the spacecraft with the given ID is not found
     */
    public Spacecraft updateSpacecraft(Long id, Spacecraft spacecraft){
        logger.info("Updating spacecraft with ID: {}", id);
        return spacecraftRepository.findById(id).map(
                        ship -> {
                            ship.setName(spacecraft.getName());
                            ship.setType(spacecraft.getType());
                            ship.setOrigin(spacecraft.getOrigin());
                            logger.info("Spacecraft with ID {} updated", id);
                            return spacecraftRepository.save(ship);
                        })
                .orElseThrow(() -> {
                    logger.error("Spacecraft with ID {} not found for update", id);
                    return new EntityNotFoundException("Spacecraft not found with id: " + id);
                });
    }

    /**
     * Deletes a spacecraft from the system by its ID.
     *
     * @param id the ID of the spacecraft to delete
     */
    public void deleteSpacecraft(Long id) {
        logger.info("Attempting to delete spacecraft with ID: {}", id);
        if (!spacecraftRepository.existsById(id)) {
            logger.error("Spacecraft with ID {} not found for deletion", id);
            throw new EntityNotFoundException("Spacecraft not found with id: " + id);
        }
        spacecraftRepository.deleteById(id);
        logger.info("Spacecraft with ID {} deleted", id);
    }

}
