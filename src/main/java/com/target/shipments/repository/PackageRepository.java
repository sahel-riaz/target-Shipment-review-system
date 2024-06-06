package com.target.shipments.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.target.shipments.entity.Package;

public interface PackageRepository extends JpaRepository<Package, String> {
}
