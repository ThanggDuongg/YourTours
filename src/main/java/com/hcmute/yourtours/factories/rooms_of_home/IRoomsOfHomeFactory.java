package com.hcmute.yourtours.factories.rooms_of_home;

import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.factory.IDataFactory;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeCreateModel;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeDetail;
import com.hcmute.yourtours.models.rooms_of_home.RoomOfHomeInfo;

import java.util.List;
import java.util.UUID;

public interface IRoomsOfHomeFactory extends IDataFactory<UUID, RoomOfHomeInfo, RoomOfHomeDetail> {
    void createListWithHomeId(UUID homeId, List<RoomOfHomeCreateModel> listCreate) throws InvalidException;
}
