package com.hcmute.yourtours.models.statistic.host.models;

import com.hcmute.yourtours.models.statistic.common.RevenueStatistic;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OwnerStatistic implements Serializable {
    private Long totalNumberOfBooking;
    private Long totalNumberOfBookingFinish;
    private transient List<HomeBookingStatistic> homeStatistic;
    private transient List<RevenueStatistic> revenueStatistics;
}
