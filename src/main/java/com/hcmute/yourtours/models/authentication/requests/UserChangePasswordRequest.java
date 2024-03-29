package com.hcmute.yourtours.models.authentication.requests;

import com.hcmute.yourtours.libs.util.constant.RegexUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserChangePasswordRequest {
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = RegexUtils.PASSWORD_REGEX, message = "Nhập không đúng định dạng mật khẩu")
    private String newPassword;

    @NotNull
    @NotBlank
    @Pattern(regexp = RegexUtils.PASSWORD_REGEX, message = "Nhập không đúng định dạng mật khẩu")
    private String confirmPassword;
}
