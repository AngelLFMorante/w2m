package com.w2m.app.domino.repository;

import com.w2m.app.domino.model.Spacecraft;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing spacecraft data in the database.
 * Extends JpaRepository to leverage basic CRUD operations and custom query methods.
 *
 * @author Angel Lf Morante
 * @version 1.0
 */
public interface SpacecraftRepository extends JpaRepository <Spacecraft, Long> {

    /**
     * Finds spacecrafts whose name contains the specified string.
     * This method is used to search for spacecraft by a part of their name.
     *
     * @param name the string to search for within spacecraft names
     * @return a list of spacecrafts whose name contains the specified string
     */
    List<Spacecraft> findByNameContaining(String name);
}
