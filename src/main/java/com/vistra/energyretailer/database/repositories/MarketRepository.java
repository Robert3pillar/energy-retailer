package com.vistra.energyretailer.database.repositories;

import com.vistra.energyretailer.database.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

}
