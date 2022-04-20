package com.example.serviceremoteredirect.repository;

import com.example.serviceremoteredirect.entity.Country;
import com.example.serviceremoteredirect.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
