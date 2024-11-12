package com.w2m.app.application.service;

import com.w2m.app.domino.Spacecraft;
import com.w2m.app.domino.SpacecraftRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpacecraftService {

    private final SpacecraftRepository spacecraftRepository;

    public SpacecraftService(SpacecraftRepository spacecraftRepository) {
        this.spacecraftRepository = spacecraftRepository;
    }

    public Page<Spacecraft> getAllSpacecraft(Pageable pageable){
        return spacecraftRepository.findAll(pageable);
    }

    @Cacheable("spacecraft")
    public Optional<Spacecraft> getSpacecraftById(Long id){
        return spacecraftRepository.findById(id);
    }

    public List<Spacecraft> searchSpacecraftByName(String name){
        return spacecraftRepository.findByNameContaining(name);
    }

    public Spacecraft createSpacecraft(Spacecraft spacecraft){
        return spacecraftRepository.save(spacecraft);
    }

    public Spacecraft updateSpacecraft(Long id, Spacecraft spacecraft){
        Spacecraft ship = spacecraftRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spacecraft not found with id: " + id));

        ship.setName(spacecraft.getName());
        ship.setType(spacecraft.getType());
        ship.setOrigin(spacecraft.getOrigin());

        return spacecraftRepository.save(ship);
    }

    public void deleteSpacecraft(Long id){
        spacecraftRepository.deleteById(id);
    }

}
