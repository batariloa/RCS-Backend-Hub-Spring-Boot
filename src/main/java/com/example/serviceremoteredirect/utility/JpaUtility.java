package com.example.serviceremoteredirect.utility;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.repository.LocationRepository;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import com.example.serviceremoteredirect.repository.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Component
public class JpaUtility {

@Autowired
LoggedAccessRepository loggedAccessRepository;

@Autowired
   LocationRepository locationRepository;

    @Autowired
     OperatingSystemRepository operatingSystemRepository;


//check fields before saving, to ensure there are no duplicates
    public  void validateBeforeSaving(LoggedAccess loggedAccess){

        //check if an hour has passed between the last log for this username
            if(!isPassedAnHour(loggedAccess))
                return;

        //check if the ip is already inside the table
            validateIp(loggedAccess);

        //check if the exact OS entry is already inside the table
             validateOS(loggedAccess);



        loggedAccessRepository.save(loggedAccess);
    }


//check if an hour has passed between the last log for this username
    boolean isPassedAnHour(LoggedAccess loggedAccess) {

        //if user doesnt exist return
        if (!loggedAccessRepository.existsByUsername(loggedAccess.getUsername()))
            return true;

        //get last log's time for this user
        LocalDateTime dateTimeLast =
                LocalDateTime.parse(loggedAccessRepository.findTopByUsernameOrderByIdDesc(loggedAccess.getUsername()).getTimestamp());

        //get current time in Netherlands
        LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of("GMT+2")).toLocalDateTime();

        if (now.isAfter(dateTimeLast.plusHours(1)))
            return true;

        else
            return false;


    }

    //check if the ip is already inside the table
     void validateIp(LoggedAccess loggedAccess){

         if(locationRepository.existsByIp(loggedAccess.getLocation().getIp()))
             loggedAccess.setLocation(locationRepository.findByIp(loggedAccess.getLocation().getIp()));
     }

    //check if the exact OS entry is already inside the table
     void validateOS(LoggedAccess loggedAccess){

         if(operatingSystemRepository.existsByArchitectureAndNameAndVersion(
                 loggedAccess.getOperatingSystem().getArchitecture(),
                 loggedAccess.getOperatingSystem().getName(),
                 loggedAccess.getOperatingSystem().getVersion()
         ))
             loggedAccess.setOperatingSystem(operatingSystemRepository.findByArchitectureAndNameAndVersion(
                     loggedAccess.getOperatingSystem().getArchitecture(),
                     loggedAccess.getOperatingSystem().getName(),
                     loggedAccess.getOperatingSystem().getVersion()
             ));

     }
    }