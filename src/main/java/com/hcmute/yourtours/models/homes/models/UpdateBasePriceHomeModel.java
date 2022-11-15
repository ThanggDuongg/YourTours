package com.hcmute.yourtours.models.homes.models;

import com.hcmute.yourtours.libs.model.BaseData;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class UpdateBasePriceHomeModel extends BaseData<UUID> {
    @Min(value = 0, message = "Giá tiền không được phép nhỏ hơn 0")
    @NotNull
    private Double costPerNightDefault;
}
