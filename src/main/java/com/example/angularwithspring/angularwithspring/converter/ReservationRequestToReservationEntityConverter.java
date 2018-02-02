package com.example.angularwithspring.angularwithspring.converter;

import com.example.angularwithspring.angularwithspring.entity.ReservationEntity;
import com.example.angularwithspring.angularwithspring.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter  implements Converter<ReservationRequest, ReservationEntity>{
    @Override
    public ReservationEntity convert(ReservationRequest reservationRequest) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(reservationRequest.getCheckin());
        reservationEntity.setCheckout(reservationRequest.getCheckout());

        if (null != reservationRequest.getId())
            reservationEntity.setId(reservationRequest.getId());

        return reservationEntity;
    }
}
