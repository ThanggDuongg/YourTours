package com.hcmute.yourtours.factories.user;

import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.factory.IDataFactory;
import com.hcmute.yourtours.models.requests.UserChangePasswordRequest;
import com.hcmute.yourtours.models.response.ChangePasswordResponse;
import com.hcmute.yourtours.models.user.UserDetail;
import com.hcmute.yourtours.models.user.UserInfo;

import java.util.UUID;

public interface IUserFactory extends IDataFactory<UUID, UserInfo, UserDetail> {
    ChangePasswordResponse userChangePassword(UserChangePasswordRequest request) throws InvalidException;
}