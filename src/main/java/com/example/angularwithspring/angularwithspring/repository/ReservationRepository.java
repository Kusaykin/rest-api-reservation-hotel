package com.example.angularwithspring.angularwithspring.repository;

import com.example.angularwithspring.angularwithspring.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

}
