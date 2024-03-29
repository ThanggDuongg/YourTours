package com.hcmute.yourtours.repositories;

import com.hcmute.yourtours.entities.ImagesHomeCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagesHomeRepository extends JpaRepository<ImagesHomeCommand, Long> {
    Optional<ImagesHomeCommand> findByImageId(UUID imageId);

    @Modifying
    void deleteAllByHomeId(UUID homeId);

    List<ImagesHomeCommand> findAllByHomeId(UUID homeId);
}
