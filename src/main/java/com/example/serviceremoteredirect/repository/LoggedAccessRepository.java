package com.example.serviceremoteredirect.repository;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedAccessRepository extends JpaRepository<LoggedAccess, Long> {

   LoggedAccess findTopByUsernameOrderByIdDesc(String username);



   boolean existsByUsername(String username);

}
