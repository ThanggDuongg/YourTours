package com.hcmute.yourtours.repositories;

import com.hcmute.yourtours.commands.RoomCategoriesCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomCategoriesRepository extends JpaRepository<RoomCategoriesCommand, Long> {
    Optional<RoomCategoriesCommand> findByRoomCategoryId(UUID roomCategoryId);
}
