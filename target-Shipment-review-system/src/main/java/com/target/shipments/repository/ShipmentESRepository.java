package com.target.shipments.repository;

import com.target.shipments.model.ShipmentES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentESRepository extends ElasticsearchRepository<ShipmentES,String> {
    
}
