package com.hcmute.yourtours.repositories;

import com.hcmute.yourtours.entities.BedsOfHomeCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BedsOfHomeRepository extends JpaRepository<BedsOfHomeCommand, Long> {
    Optional<BedsOfHomeCommand> findByBedOfHomeId(UUID bedOfHomeId);

    @Query(
            nativeQuery = true,
            value = "select COALESCE(sum(a.amount), 0) " +
                    "from beds_of_home a, " +
                    "     rooms_of_home b " +
                    "where a.room_of_home_id = b.room_of_home_id " +
                    "  and b.home_id = :homeId "
    )
    Integer countNumberOfBedByHomeId(@Param("homeId") UUID homeId);

    Optional<BedsOfHomeCommand> findByRoomOfHomeIdAndCategoryId(UUID roomHomeId, UUID categoryId);

    List<BedsOfHomeCommand> findAllByRoomOfHomeId(UUID roomHomeId);
}
