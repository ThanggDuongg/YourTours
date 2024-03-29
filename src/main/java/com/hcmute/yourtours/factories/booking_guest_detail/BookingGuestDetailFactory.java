package com.hcmute.yourtours.factories.booking_guest_detail;

import com.hcmute.yourtours.entities.BookingHomeGuestDetailCommand;
import com.hcmute.yourtours.exceptions.YourToursErrorCode;
import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.factory.BasePersistDataFactory;
import com.hcmute.yourtours.models.booking_guest_detail.BookingGuestDetailDetail;
import com.hcmute.yourtours.models.booking_guest_detail.BookingGuestDetailInfo;
import com.hcmute.yourtours.repositories.BookingGuestDetailRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingGuestDetailFactory
        extends BasePersistDataFactory<UUID, BookingGuestDetailInfo, BookingGuestDetailDetail, Long, BookingHomeGuestDetailCommand>
        implements IBookingGuestDetailFactory {

    private final BookingGuestDetailRepository bookingGuestDetailRepository;

    protected BookingGuestDetailFactory
            (BookingGuestDetailRepository repository) {
        super(repository);
        this.bookingGuestDetailRepository = repository;
    }

    @Override
    @NonNull
    protected Class<BookingGuestDetailDetail> getDetailClass() {
        return BookingGuestDetailDetail.class;
    }

    @Override
    public BookingHomeGuestDetailCommand createConvertToEntity(BookingGuestDetailDetail detail) throws InvalidException {
        if (detail == null) {
            return null;
        }

        return BookingHomeGuestDetailCommand.builder()
                .booking(detail.getBooking())
                .number(detail.getNumber())
                .guestCategory(detail.getGuestCategory())
                .build();
    }

    @Override
    public void updateConvertToEntity(BookingHomeGuestDetailCommand entity, BookingGuestDetailDetail detail) throws InvalidException {
        entity.setBooking(detail.getBooking());
        entity.setGuestCategory(detail.getGuestCategory());
        entity.setNumber(detail.getNumber());
    }

    @Override
    public BookingGuestDetailDetail convertToDetail(BookingHomeGuestDetailCommand entity) throws InvalidException {
        if (entity == null) {
            return null;
        }

        return BookingGuestDetailDetail.builder()
                .number(entity.getNumber())
                .booking(entity.getBooking())
                .guestCategory(entity.getGuestCategory())
                .id(entity.getBookingGuestDetailId())
                .build();
    }

    @Override
    public BookingGuestDetailInfo convertToInfo(BookingHomeGuestDetailCommand entity) throws InvalidException {
        if (entity == null) {
            return null;
        }

        return BookingGuestDetailInfo.builder()
                .number(entity.getNumber())
                .booking(entity.getBooking())
                .guestCategory(entity.getGuestCategory())
                .id(entity.getBookingGuestDetailId())
                .build();
    }

    @Override
    protected Long convertId(UUID id) throws InvalidException {
        Optional<BookingHomeGuestDetailCommand> optional = bookingGuestDetailRepository.findByBookingGuestDetailId(id);
        if (optional.isEmpty()) {
            throw new InvalidException(YourToursErrorCode.NOT_FOUND_BOOKING_GUEST_DETAIL);
        }
        return optional.get().getId();
    }


    @Override
    public void createListModel(UUID bookingId, List<BookingGuestDetailDetail> listDetail) throws InvalidException {
        if (listDetail == null) {
            return;
        }

        List<BookingHomeGuestDetailCommand> listDelete = bookingGuestDetailRepository.findAllByBooking(bookingId);

        for (BookingHomeGuestDetailCommand item : listDelete) {
            deleteModel(item.getBookingGuestDetailId(), null);
        }

        for (BookingGuestDetailDetail item : listDetail) {
            item.setBooking(bookingId);
            createModel(item);
        }
    }

    @Override
    public Integer getNumberGuestsOfBooking(UUID booking) {
        return bookingGuestDetailRepository.sumNumberGuestsOfBooking(booking);
    }
}
