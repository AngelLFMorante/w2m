package com.w2m.app.domino;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpacecraftRepository extends JpaRepository <Spacecraft, Long> {
    List<Spacecraft> findByNameContaining(String name);
}
