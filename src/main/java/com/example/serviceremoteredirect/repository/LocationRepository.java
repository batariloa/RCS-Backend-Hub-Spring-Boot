package com.example.serviceremoteredirect.repository;

import com.example.serviceremoteredirect.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByIp(String ip);

   boolean existsByIp(String ip);
}
