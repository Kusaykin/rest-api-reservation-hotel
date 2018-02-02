package com.example.angularwithspring.angularwithspring.rest;

import com.example.angularwithspring.angularwithspring.converter.RoomEntityToReservableRoomResponseConverter;
import com.example.angularwithspring.angularwithspring.entity.ReservationEntity;
import com.example.angularwithspring.angularwithspring.entity.RoomEntity;
import com.example.angularwithspring.angularwithspring.model.request.ReservationRequest;
import com.example.angularwithspring.angularwithspring.model.response.ReservableRoomResponse;
import com.example.angularwithspring.angularwithspring.model.response.ReservationResponse;
import com.example.angularwithspring.angularwithspring.repository.PageableRoomRepository;
import com.example.angularwithspring.angularwithspring.repository.ReservationRepository;
import com.example.angularwithspring.angularwithspring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

    @Autowired
    PageableRoomRepository pageableRoomRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ConversionService conversionService;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout,
                    Pageable pageable) {

        Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

        return roomEntityList.map(new RoomEntityToReservableRoomResponseConverter());

    }

    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {

        RoomEntity roomEntity = roomRepository.findById(roomId);

        return new ResponseEntity<RoomEntity>(roomEntity, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservatin(
            @RequestBody
                    ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);

        RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId());
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);
        reservationEntity.setRoomEntity(roomEntity);

        ReservationResponse reservationResponse = conversionService.convert(reservationEntity, ReservationResponse.class);


        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);

    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservatin(
            @RequestBody
                    ReservationRequest reservationRequest) {

        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);

    }

    @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}