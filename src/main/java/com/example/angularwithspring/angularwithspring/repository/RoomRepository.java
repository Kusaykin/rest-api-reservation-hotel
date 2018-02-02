package com.example.angularwithspring.angularwithspring.repository;

import com.example.angularwithspring.angularwithspring.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;


public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    RoomEntity findById(Long id);

}
