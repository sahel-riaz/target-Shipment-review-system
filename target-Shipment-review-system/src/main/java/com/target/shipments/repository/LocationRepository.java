package com.target.shipments.repository;

import com.target.shipments.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("Select l from Location l where l.name = ?1 and l.identity = ?2 and l.address = ?3 and l.contact = ?4")
    Location findLocation(String name, String identity, String address, String contact);
}
