package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.PollingStation;

import java.util.List;

public interface PollingStationRepository {
    List<PollingStation> getPollingStationsByID(String id);

}
