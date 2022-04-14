package com.example.serviceremoteredirect.repository;

import com.example.serviceremoteredirect.entity.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {

    OperatingSystem findByArchitectureAndNameAndVersion(String architecutre, String name, String version);
    boolean existsByArchitectureAndNameAndVersion(String architecutre, String name, String version);

}
