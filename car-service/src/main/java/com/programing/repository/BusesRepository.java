package com.programing.repository;

import com.programing.entity.Buses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusesRepository extends JpaRepository<Buses, Long> {
}
