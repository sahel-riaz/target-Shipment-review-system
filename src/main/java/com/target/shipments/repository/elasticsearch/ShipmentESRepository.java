package com.target.shipments.repository.elasticsearch;

import com.target.shipments.model.ShipmentES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentESRepository extends ElasticsearchRepository<ShipmentES, String> {
    List<ShipmentES> findByStatus(String status);
    long countByStatus(String status);
    List<ShipmentES> findByCurrency(String currency);
    void deleteByShipmentId(String shipmentId);
    
}

