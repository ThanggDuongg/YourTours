package com.hcmute.yourtours.factories.rooms_of_home;

import com.hcmute.yourtours.commands.RoomsOfHomeCommand;
import com.hcmute.yourtours.exceptions.YourToursErrorCode;
import com.hcmute.yourtours.factories.room_categories.IRoomCategoriesFactory;
import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.factory.BasePersistDataFactory;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeCreateModel;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeDetail;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeInfo;
import com.hcmute.yourtours.repositories.RoomsOfHomeRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoomsOfHomeFactory
        extends BasePersistDataFactory<UUID, RoomOfHomeInfo, RoomOfHomeDetail, Long, RoomsOfHomeCommand>
        implements IRoomsOfHomeFactory {

    private final RoomsOfHomeRepository roomsOfHomeRepository;
    private final IRoomCategoriesFactory iRoomCategoriesFactory;

    protected RoomsOfHomeFactory(RoomsOfHomeRepository repository,
                                 IRoomCategoriesFactory iRoomCategoriesFactory) {
        super(repository);
        this.roomsOfHomeRepository = repository;
        this.iRoomCategoriesFactory = iRoomCategoriesFactory;
    }

    @Override
    @NonNull
    protected Class<RoomOfHomeDetail> getDetailClass() {
        return RoomOfHomeDetail.class;
    }

    @Override
    public RoomsOfHomeCommand createConvertToEntity(RoomOfHomeDetail detail) throws InvalidException {
        if (detail == null) {
            return null;
        }
        return RoomsOfHomeCommand.builder()
                .description(detail.getDescription())
                .homeId(detail.getHomeId())
                .categoryId(detail.getCategoryId())
                .build();
    }

    @Override
    public void updateConvertToEntity(RoomsOfHomeCommand entity, RoomOfHomeDetail detail) throws InvalidException {
        entity.setDescription(detail.getDescription());
        entity.setHomeId(detail.getHomeId());
        entity.setCategoryId(detail.getCategoryId());
    }

    @Override
    public RoomOfHomeDetail convertToDetail(RoomsOfHomeCommand entity) throws InvalidException {
        if (entity == null) {
            return null;
        }
        return RoomOfHomeDetail.builder()
                .id(entity.getRoomOfHomeId())
                .description(entity.getDescription())
                .homeId(entity.getHomeId())
                .categoryId(entity.getCategoryId())
                .build();
    }

    @Override
    public RoomOfHomeInfo convertToInfo(RoomsOfHomeCommand entity) throws InvalidException {
        if (entity == null) {
            return null;
        }
        return RoomOfHomeInfo.builder()
                .id(entity.getRoomOfHomeId())
                .description(entity.getDescription())
                .homeId(entity.getHomeId())
                .categoryId(entity.getCategoryId())
                .build();
    }

    @Override
    protected Long convertId(UUID id) throws InvalidException {
        RoomsOfHomeCommand room = roomsOfHomeRepository.findByRoomOfHomeId(id).orElse(null);
        if (room == null) {
            throw new InvalidException(YourToursErrorCode.NOT_FOUND_ROOMS_OF_HOME);
        }
        return room.getId();
    }

    @Override
    public void createListWithHomeId(UUID homeId, List<RoomOfHomeCreateModel> listCreate) throws InvalidException {
        roomsOfHomeRepository.deleteAllByHomeId(homeId);
        for (RoomOfHomeCreateModel item : listCreate) {
            if (item.getNumber() != null) {
                for (int i = 0; i < item.getNumber(); i++) {
                    RoomOfHomeDetail detail = RoomOfHomeDetail.builder()
                            .name(iRoomCategoriesFactory.getDetailModel(
                                            item.getCategoryId(), null).getName()
                                    .concat(" ")
                                    .concat(String.valueOf(i)))
                            .homeId(homeId)
                            .categoryId(item.getCategoryId())
                            .build();
                    createModel(detail);
                }
            }
        }
    }
}
