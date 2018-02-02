package com.example.angularwithspring.angularwithspring.converter;

import com.example.angularwithspring.angularwithspring.entity.ReservationEntity;
import com.example.angularwithspring.angularwithspring.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity reservationEntity) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(reservationEntity.getCheckin());
        reservationResponse.setCheckout(reservationEntity.getCheckout());

        if (null != reservationEntity.getRoomEntity())
            reservationResponse.setId(reservationEntity.getRoomEntity().getId());

        return reservationResponse;
    }
}
