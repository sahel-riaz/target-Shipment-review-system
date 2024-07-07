package com.target.shipments.repository;

import com.target.shipments.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@EnableJpaRepositories
public interface ShipmentRepository extends JpaRepository<Shipment, String> {


    List<Shipment> findByStatus(String status);
    long countByStatus(String status);

    @Query("Select s from Shipment s where s.shipping_method = ?1")
    List<Shipment> findByShipping_Method(String shipping_method);
    @Query("Select count(*) from Shipment s where s.shipping_method = ?1")

    long countByShipping_Method(String shipping_method);

    List<Shipment> findByCurrency(String currency);
    long countByCurrency(String currency);

}
