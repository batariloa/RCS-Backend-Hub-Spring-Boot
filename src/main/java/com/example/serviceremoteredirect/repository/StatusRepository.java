package com.example.serviceremoteredirect.repository;

import com.example.serviceremoteredirect.entity.MemoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository  extends JpaRepository<MemoryStatus, Long> {
}
