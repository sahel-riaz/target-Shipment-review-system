package com.target.shipments.repository;

import com.target.shipments.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ShipmentRepository extends JpaRepository<Shipment, String> {


    List<Shipment> findByStatus(String status);
    long countByStatus(String status);

   // @Query("Select s from Shipment s where s.shipping_method = ?1")
    List<Shipment> findByShippingMethod(String shippingMethod);
    //@Query("Select count(*) from Shipment s where s.shipping_method = ?1")

    long countByShippingMethod(String shippingMethod);

    List<Shipment> findByCurrency(String currency);
    long countByCurrency(String currency);

}
