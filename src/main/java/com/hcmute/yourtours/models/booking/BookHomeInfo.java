package com.hcmute.yourtours.models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcmute.yourtours.enums.BookRoomStatusEnum;
import com.hcmute.yourtours.enums.PaymentMethodMethodEnum;
import com.hcmute.yourtours.libs.model.BaseData;
import com.hcmute.yourtours.models.booking_guest_detail.BookingGuestDetailDetail;
import com.hcmute.yourtours.models.booking_surcharge_detail.BookingSurchargeDetailDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@SuperBuilder
public class BookHomeInfo extends BaseData<UUID> {

    @NotNull
    private LocalDate dateStart;

    @NotNull
    private LocalDate dateEnd;

    private String phoneNumber;

    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double cost;

    private PaymentMethodMethodEnum paymentMethod;

    private String visaAccount;

    @NotNull
    private UUID homeId;

    private UUID userId;

    private BookRoomStatusEnum status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String homeName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String customerName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalCost;

    private List<BookingSurchargeDetailDetail> surcharges;

    private List<BookingGuestDetailDetail> guests;
}
