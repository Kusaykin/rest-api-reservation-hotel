package com.example.angularwithspring.angularwithspring.converter;

import com.example.angularwithspring.angularwithspring.entity.RoomEntity;
import com.example.angularwithspring.angularwithspring.model.Links;
import com.example.angularwithspring.angularwithspring.model.Self;
import com.example.angularwithspring.angularwithspring.model.response.ReservableRoomResponse;
import com.example.angularwithspring.angularwithspring.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {
    @Override
    public ReservableRoomResponse convert(RoomEntity roomEntity) {
        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(roomEntity.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(roomEntity.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + roomEntity.getId());
        links.setSelf(self);
        reservableRoomResponse.setLinks(links);

        return reservableRoomResponse;
    }
}
