package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;

import java.util.List;

public interface PollingStationService {
    List<PollingStation> getPollingStationsByUserId(String userID);

    List<PollingStationTask> getTaskByPollingStationID(String pollingStationID);

}