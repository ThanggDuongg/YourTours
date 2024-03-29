package com.hcmute.yourtours.models.booking.filter;

import com.hcmute.yourtours.enums.BookRoomStatusEnum;
import com.hcmute.yourtours.libs.model.filter.BaseFilter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CmsBookingFilter implements BaseFilter {
    private BookRoomStatusEnum status;
    private LocalDate dataStart;
}
